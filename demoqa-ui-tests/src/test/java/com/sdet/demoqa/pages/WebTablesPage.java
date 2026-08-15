package com.sdet.demoqa.pages;

import com.sdet.framework.config.Config;
import com.sdet.framework.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebTablesPage {

  private static final String PATH = "/webtables";

  private final WebDriver driver;

  private static final By ADD_BUTTON = By.id("addNewRecordButton");
  private static final By FIRST_NAME = By.id("firstName");
  private static final By LAST_NAME = By.id("lastName");
  private static final By EMAIL = By.id("userEmail");
  private static final By AGE = By.id("age");
  private static final By SALARY = By.id("salary");
  private static final By DEPARTMENT = By.id("department");
  private static final By SUBMIT = By.id("submit");
  private static final By SEARCH = By.id("searchBox");
  private static final By TABLE_BODY = By.cssSelector(".rt-tbody");

  public WebTablesPage(WebDriver driver) {
    this.driver = driver;
  }

  public WebTablesPage open() {
    driver.get(Config.get("base.url", "https://demoqa.com") + PATH);
    Waits.visible(driver, ADD_BUTTON);
    return this;
  }

  public WebTablesPage addRecord(String first, String last, String email, String age,
                                 String salary, String department) {
    Waits.click(driver, ADD_BUTTON);
    Waits.type(driver, FIRST_NAME, first);
    Waits.type(driver, LAST_NAME, last);
    Waits.type(driver, EMAIL, email);
    Waits.type(driver, AGE, age);
    Waits.type(driver, SALARY, salary);
    Waits.type(driver, DEPARTMENT, department);
    Waits.click(driver, SUBMIT);
    Waits.visible(driver, TABLE_BODY);
    return this;
  }

  public WebTablesPage search(String query) {
    Waits.type(driver, SEARCH, query);
    Waits.visible(driver, TABLE_BODY);
    return this;
  }

  public String tableText() {
    return Waits.visible(driver, TABLE_BODY).getText();
  }
}
