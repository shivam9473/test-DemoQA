package com.sdet.framework.driver;

import com.sdet.framework.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {

  private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

  private DriverFactory() {}

  public static WebDriver getDriver() {
    if (DRIVER.get() == null) {
      DRIVER.set(createDriver());
    }
    return DRIVER.get();
  }

  public static void quitDriver() {
    WebDriver driver = DRIVER.get();
    if (driver != null) {
      driver.quit();
      DRIVER.remove();
    }
  }

  private static WebDriver createDriver() {
    String browser = Config.get("browser", "chrome").toLowerCase();
    boolean headless = Config.bool("headless", false);
    String gridUrl = Config.get("grid.url");

    if (gridUrl != null && !gridUrl.isBlank()) {
      return createRemoteDriver(gridUrl, browser, headless);
    }

    return switch (browser) {
      case "firefox" -> createFirefox(headless);
      default -> createChrome(headless);
    };
  }

  private static WebDriver createChrome(boolean headless) {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = baseChromeOptions(headless);
    WebDriver driver = new ChromeDriver(options);
    applyTimeouts(driver);
    return driver;
  }

  private static WebDriver createFirefox(boolean headless) {
    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions options = new FirefoxOptions();
    if (headless) {
      options.addArguments("-headless");
    }
    WebDriver driver = new FirefoxDriver(options);
    applyTimeouts(driver);
    return driver;
  }

  private static WebDriver createRemoteDriver(String gridUrl, String browser, boolean headless) {
    try {
      ChromeOptions options = baseChromeOptions(headless);
      if ("firefox".equals(browser)) {
        return new RemoteWebDriver(new URL(gridUrl), new FirefoxOptions());
      }
      WebDriver driver = new RemoteWebDriver(new URL(gridUrl), options);
      applyTimeouts(driver);
      return driver;
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Invalid grid.url: " + gridUrl, e);
    }
  }

  private static ChromeOptions baseChromeOptions(boolean headless) {
    ChromeOptions options = new ChromeOptions();
    if (headless) {
      options.addArguments("--headless=new", "--window-size=1920,1080", "--disable-gpu");
    }
    options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications");
    Map<String, Object> prefs = new HashMap<>();
    prefs.put("profile.default_content_setting_values.notifications", 2);
    options.setExperimentalOption("prefs", prefs);
    return options;
  }

  private static void applyTimeouts(WebDriver driver) {
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
        Config.integer("page.load.timeout.sec", 30)));
    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(
        Config.integer("script.timeout.sec", 20)));
  }
}
