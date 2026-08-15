package com.sdet.framework.utils;

import com.sdet.framework.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class Waits {

  private static final Duration DEFAULT = Duration.ofSeconds(
      Config.integer("explicit.wait.sec", 15));

  private Waits() {}

  public static WebElement visible(WebDriver driver, By locator) {
    return new WebDriverWait(driver, DEFAULT)
        .until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public static WebElement clickable(WebDriver driver, By locator) {
    WebElement element = new WebDriverWait(driver, DEFAULT)
        .until(ExpectedConditions.elementToBeClickable(locator));
    scrollIntoView(driver, element);
    return element;
  }

  public static void click(WebDriver driver, By locator) {
    WebElement element = clickable(driver, locator);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  public static void type(WebDriver driver, By locator, String value) {
    WebElement element = visible(driver, locator);
    scrollIntoView(driver, element);
    element.click();

    if ("textarea".equalsIgnoreCase(element.getTagName())) {
      element.clear();
      element.sendKeys(value);
      return;
    }

    ((JavascriptExecutor) driver).executeScript(
        "const el = arguments[0]; const val = arguments[1];"
            + "const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;"
            + "setter.call(el, val);"
            + "el.dispatchEvent(new Event('input', { bubbles: true }));"
            + "el.dispatchEvent(new Event('change', { bubbles: true }));",
        element, value);
  }

  public static boolean urlContains(WebDriver driver, String fragment) {
    return new WebDriverWait(driver, DEFAULT)
        .until(ExpectedConditions.urlContains(fragment));
  }

  public static void scrollIntoView(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({block: 'center'});", element);
  }
}
