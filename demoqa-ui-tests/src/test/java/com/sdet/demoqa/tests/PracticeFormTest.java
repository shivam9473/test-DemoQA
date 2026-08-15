package com.sdet.demoqa.tests;

import com.sdet.demoqa.base.BaseUiTest;
import com.sdet.demoqa.pages.PracticeFormPage;
import com.sdet.framework.annotations.TestCase;
import com.sdet.framework.listeners.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("DemoQA Forms")
@Feature("Practice Form")
public class PracticeFormTest extends BaseUiTest {

  @Test(retryAnalyzer = RetryAnalyzer.class)
  @TestCase(id = "DQ-PF-001", module = "forms")
  @Description("Complete practice form with required fields and verify submission modal")
  public void submitPracticeFormShowsModal() {
    var form = new PracticeFormPage(driver).open();
    form.fillRequiredFields("Shivam", "Kumar", "shivam.test@example.com", "9876543210")
        .withSubject("Computer Science")
        .selectSportsHobby()
        .withAddress("221B Baker Street, London")
        .submit();

    assertTrue(form.isConfirmationVisible(), "Confirmation modal should appear");
    assertEquals(form.confirmationTitle(), "Thanks for submitting the form");
  }
}
