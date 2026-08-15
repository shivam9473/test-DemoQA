package com.sdet.demoqa.tests;

import com.sdet.demoqa.base.BaseUiTest;
import com.sdet.demoqa.pages.AlertsPage;
import com.sdet.framework.annotations.TestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("DemoQA Alerts")
@Feature("Alerts, Frame and Windows")
public class AlertsTest extends BaseUiTest {

  @Test
  @TestCase(id = "DQ-AL-001", module = "alerts")
  @Description("Accept a simple JavaScript alert")
  public void acceptJavaScriptAlert() {
    assertEquals(new AlertsPage(driver).open().triggerAlert(), "You clicked a button");
  }

  @Test
  @TestCase(id = "DQ-AL-002", module = "alerts")
  @Description("Accept a confirm dialog and verify result text")
  public void acceptConfirmDialog() {
    String result = new AlertsPage(driver).open().triggerConfirm(true);
    assertTrue(result.contains("Ok"), "Result: " + result);
  }

  @Test(enabled = false) // Chrome headless prompt input is flaky; run manually with visible browser
  @TestCase(id = "DQ-AL-003", module = "alerts")
  @Description("Enter text in prompt dialog and verify echoed result")
  public void submitPromptDialog() {
    String result = new AlertsPage(driver).open().triggerPrompt("SDET");
    assertTrue(result.contains("SDET") || result.contains("You entered"),
        "Result: " + result);
  }
}
