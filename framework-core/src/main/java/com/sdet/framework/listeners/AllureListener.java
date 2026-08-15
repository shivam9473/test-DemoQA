package com.sdet.framework.listeners;

import com.sdet.framework.driver.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AllureListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    attachScreenshot();
    Allure.addAttachment("Failure reason",
        new ByteArrayInputStream(result.getThrowable().toString().getBytes(StandardCharsets.UTF_8)));
  }

  private void attachScreenshot() {
    WebDriver driver = DriverFactory.getDriver();
    if (driver instanceof TakesScreenshot screenshot) {
      byte[] png = screenshot.getScreenshotAs(OutputType.BYTES);
      Allure.addAttachment("Screenshot on failure", "image/png", new ByteArrayInputStream(png), ".png");
    }
  }
}
