package com.sdet.demoqa.tests;

import com.sdet.demoqa.base.BaseUiTest;
import com.sdet.demoqa.pages.TextBoxPage;
import com.sdet.framework.annotations.TestCase;
import com.sdet.framework.data.JsonDataReader;
import com.sdet.framework.listeners.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

@Epic("DemoQA Elements")
@Feature("Text Box")
public class TextBoxTest extends BaseUiTest {

  @DataProvider(name = "textBoxData")
  public Object[][] textBoxData() {
    List<Map<String, String>> rows = JsonDataReader.readRows("testdata/textbox-users.json");
    Object[][] data = new Object[rows.size()][4];
    for (int i = 0; i < rows.size(); i++) {
      Map<String, String> row = rows.get(i);
      data[i][0] = row.get("name");
      data[i][1] = row.get("email");
      data[i][2] = row.get("currentAddress");
      data[i][3] = row.get("permanentAddress");
    }
    return data;
  }

  @Test(dataProvider = "textBoxData", retryAnalyzer = RetryAnalyzer.class)
  @TestCase(id = "DQ-TB-001", module = "elements")
  @Description("Submit text box form and verify output panel contains entered values")
  public void submitTextBoxShowsOutput(String name, String email, String current, String permanent) {
    String output = new TextBoxPage(driver)
        .open()
        .fillForm(name, email, current, permanent)
        .submit()
        .outputText();

    assertTrue(output.contains(name), "Output should contain name");
    assertTrue(output.contains(email), "Output should contain email");
    assertTrue(output.contains(current), "Output should contain current address");
    assertTrue(output.contains(permanent), "Output should contain permanent address");
  }
}
