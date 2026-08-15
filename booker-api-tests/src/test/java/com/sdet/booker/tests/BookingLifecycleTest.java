package com.sdet.booker.tests;

import com.sdet.booker.client.BookerClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class BookingLifecycleTest {

  private String token;
  private int bookingId;

  @BeforeClass
  public void setup() {
    BookerClient.baseUri("https://restful-booker.herokuapp.com");
    token = BookerClient.createToken("admin", "password123");
  }

  @Test(priority = 1)
  public void createBookingReturns200WithId() {
    Map<String, Object> body = sampleBooking("Shivam", "Kumar", 120, true);

    Response response = BookerClient.createBooking(body);
    response.then()
        .statusCode(200)
        .body("bookingid", notNullValue())
        .body("booking.firstname", equalTo("Shivam"))
        .body(matchesJsonSchemaInClasspath("schemas/create-booking-schema.json"));

    bookingId = response.jsonPath().getInt("bookingid");
  }

  @Test(priority = 2, dependsOnMethods = "createBookingReturns200WithId")
  public void getBookingById() {
    BookerClient.getBooking(bookingId).then()
        .statusCode(200)
        .body("firstname", equalTo("Shivam"))
        .body("lastname", equalTo("Kumar"));
  }

  @Test(priority = 3, dependsOnMethods = "createBookingReturns200WithId")
  public void updateBookingWithAuthToken() {
    Map<String, Object> updated = sampleBooking("Shivam", "Updated", 150, false);

    BookerClient.updateBooking(bookingId, token, updated).then()
        .statusCode(200)
        .body("lastname", equalTo("Updated"))
        .body("totalprice", equalTo(150));
  }

  @Test(priority = 4, dependsOnMethods = "updateBookingWithAuthToken")
  public void deleteBookingWithAuthToken() {
    BookerClient.deleteBooking(bookingId, token).then()
        .statusCode(201);

    BookerClient.getBooking(bookingId).then()
        .statusCode(404);
  }

  @Test
  public void healthPing() {
    io.restassured.RestAssured.get("/ping").then()
        .statusCode(201)
        .body(equalTo("Created"));
  }

  private static Map<String, Object> sampleBooking(String first, String last, int price, boolean deposit) {
    Map<String, Object> booking = new LinkedHashMap<>();
    booking.put("firstname", first);
    booking.put("lastname", last);
    booking.put("totalprice", price);
    booking.put("depositpaid", deposit);
    booking.put("bookingdates", Map.of("checkin", "2026-09-01", "checkout", "2026-09-05"));
    booking.put("additionalneeds", "Breakfast");
    return booking;
  }
}
