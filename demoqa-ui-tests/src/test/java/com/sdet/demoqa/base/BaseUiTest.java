package com.sdet.demoqa.base;

import com.sdet.framework.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseUiTest {

  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void startBrowser() {
    driver = DriverFactory.getDriver();
    driver.manage().window().maximize();
  }

  @AfterMethod(alwaysRun = true)
  public void stopBrowser() {
    DriverFactory.quitDriver();
  }
}
