package com.sdet.booker.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public final class BookerClient {

  private BookerClient() {}

  public static void baseUri(String uri) {
    RestAssured.baseURI = uri;
  }

  public static String createToken(String username, String password) {
    Response response = given()
        .contentType(ContentType.JSON)
        .body(Map.of("username", username, "password", password))
        .post("/auth");
    return response.jsonPath().getString("token");
  }

  public static Response createBooking(Map<String, Object> body) {
    return given()
        .contentType(ContentType.JSON)
        .body(body)
        .post("/booking");
  }

  public static Response getBooking(int id) {
    return given().get("/booking/" + id);
  }

  public static Response updateBooking(int id, String token, Map<String, Object> body) {
    return given()
        .contentType(ContentType.JSON)
        .header("Cookie", "token=" + token)
        .body(body)
        .put("/booking/" + id);
  }

  public static Response deleteBooking(int id, String token) {
    return given()
        .header("Cookie", "token=" + token)
        .delete("/booking/" + id);
  }
}
