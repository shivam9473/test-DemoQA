package com.sdet.demoqa.tests;

import com.sdet.demoqa.base.BaseUiTest;
import com.sdet.demoqa.pages.WebTablesPage;
import com.sdet.framework.annotations.TestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("DemoQA Widgets")
@Feature("Web Tables")
public class WebTablesTest extends BaseUiTest {

  @Test(enabled = false) // DemoQA webtables layout varies; run locally with visible browser
  @TestCase(id = "DQ-WT-001", module = "widgets")
  @Description("Filter web table rows using the search box")
  public void searchFiltersTableRows() {
    var tables = new WebTablesPage(driver).open();
    tables.search("Cierra");

    String body = tables.tableText();
    assertTrue(body.contains("Cierra"), "Filtered table should show matching row");
    assertTrue(body.contains("Vega"), "Row should include last name from seed data");
  }
}
