package com.sdet.demoqa.pages;

import com.sdet.framework.config.Config;
import com.sdet.framework.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBoxPage {

  private static final String PATH = "/text-box";

  private final WebDriver driver;

  private static final By FULL_NAME = By.id("userName");
  private static final By EMAIL = By.id("userEmail");
  private static final By CURRENT_ADDRESS = By.id("currentAddress");
  private static final By PERMANENT_ADDRESS = By.id("permanentAddress");
  private static final By SUBMIT = By.id("submit");
  private static final By OUTPUT = By.id("output");

  public TextBoxPage(WebDriver driver) {
    this.driver = driver;
  }

  public TextBoxPage open() {
    driver.get(Config.get("base.url", "https://demoqa.com") + PATH);
    Waits.visible(driver, FULL_NAME);
    return this;
  }

  public TextBoxPage fillForm(String name, String email, String current, String permanent) {
    Waits.type(driver, FULL_NAME, name);
    Waits.type(driver, EMAIL, email);
    Waits.type(driver, CURRENT_ADDRESS, current);
    Waits.type(driver, PERMANENT_ADDRESS, permanent);
    return this;
  }

  public TextBoxPage submit() {
    Waits.click(driver, SUBMIT);
    return this;
  }

  public String outputText() {
    return Waits.visible(driver, OUTPUT).getText();
  }
}
