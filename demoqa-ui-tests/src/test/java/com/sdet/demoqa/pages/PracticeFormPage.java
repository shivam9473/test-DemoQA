package com.sdet.demoqa.pages;

import com.sdet.framework.config.Config;
import com.sdet.framework.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticeFormPage {

  private static final String PATH = "/automation-practice-form";

  private final WebDriver driver;

  private static final By FIRST_NAME = By.id("firstName");
  private static final By LAST_NAME = By.id("lastName");
  private static final By EMAIL = By.id("userEmail");
  private static final By GENDER_MALE = By.cssSelector("label[for='gender-radio-1']");
  private static final By MOBILE = By.id("userNumber");
  private static final By SUBJECTS = By.id("subjectsInput");
  private static final By HOBBIES_SPORTS = By.cssSelector("label[for='hobbies-checkbox-1']");
  private static final By CURRENT_ADDRESS = By.id("currentAddress");
  private static final By SUBMIT = By.id("submit");
  private static final By MODAL = By.cssSelector(".modal-content");
  private static final By MODAL_TITLE = By.id("example-modal-sizes-title-lg");

  public PracticeFormPage(WebDriver driver) {
    this.driver = driver;
  }

  public PracticeFormPage open() {
    driver.get(Config.get("base.url", "https://demoqa.com") + PATH);
    Waits.visible(driver, FIRST_NAME);
    return this;
  }

  public PracticeFormPage fillRequiredFields(String first, String last, String email, String mobile) {
    Waits.type(driver, FIRST_NAME, first);
    Waits.type(driver, LAST_NAME, last);
    Waits.type(driver, EMAIL, email);
    Waits.click(driver, GENDER_MALE);
    Waits.type(driver, MOBILE, mobile);
    return this;
  }

  public PracticeFormPage withSubject(String subject) {
    Waits.type(driver, SUBJECTS, subject);
    Waits.click(driver, By.xpath("//div[contains(@class,'subjects-auto-complete')]//div[text()='" + subject + "']"));
    return this;
  }

  public PracticeFormPage selectSportsHobby() {
    Waits.click(driver, HOBBIES_SPORTS);
    return this;
  }

  public PracticeFormPage withAddress(String address) {
    Waits.type(driver, CURRENT_ADDRESS, address);
    return this;
  }

  public PracticeFormPage submit() {
    Waits.scrollIntoView(driver, driver.findElement(SUBMIT));
    Waits.click(driver, SUBMIT);
    return this;
  }

  public boolean isConfirmationVisible() {
    return Waits.visible(driver, MODAL).isDisplayed();
  }

  public String confirmationTitle() {
    return Waits.visible(driver, MODAL_TITLE).getText();
  }
}
