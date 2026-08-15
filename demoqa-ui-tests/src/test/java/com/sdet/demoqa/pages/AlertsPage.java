package com.sdet.demoqa.pages;

import com.sdet.framework.config.Config;
import com.sdet.framework.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage {

  private static final String PATH = "/alerts";

  private final WebDriver driver;

  private static final By ALERT_BUTTON = By.id("alertButton");
  private static final By CONFIRM_BUTTON = By.id("confirmButton");
  private static final By PROMPT_BUTTON = By.id("promtButton");
  private static final By CONFIRM_RESULT = By.id("confirmResult");

  public AlertsPage(WebDriver driver) {
    this.driver = driver;
  }

  public AlertsPage open() {
    driver.get(Config.get("base.url", "https://demoqa.com") + PATH);
    Waits.visible(driver, ALERT_BUTTON);
    return this;
  }

  public String triggerAlert() {
    Waits.click(driver, ALERT_BUTTON);
    String text = driver.switchTo().alert().getText();
    driver.switchTo().alert().accept();
    return text;
  }

  public String triggerConfirm(boolean accept) {
    Waits.click(driver, CONFIRM_BUTTON);
    if (accept) {
      driver.switchTo().alert().accept();
    } else {
      driver.switchTo().alert().dismiss();
    }
    return Waits.visible(driver, CONFIRM_RESULT).getText();
  }

  public String triggerPrompt(String input) {
    Waits.click(driver, PROMPT_BUTTON);
    driver.switchTo().alert().sendKeys(input);
    driver.switchTo().alert().accept();
    return Waits.visible(driver, CONFIRM_RESULT).getText();
  }
}
