package com.sdet.demoqa.pages;

import com.sdet.framework.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SidebarMenu {

  private final WebDriver driver;

  public SidebarMenu(WebDriver driver) {
    this.driver = driver;
  }

  public void openItem(String itemText) {
    By locator = By.xpath("//span[text()='" + itemText + "']/parent::span/parent::div");
    Waits.click(driver, locator);
  }

  public TextBoxPage openTextBox() {
    openItem("Text Box");
    return new TextBoxPage(driver);
  }

  public PracticeFormPage openPracticeForm() {
    openItem("Practice Form");
    return new PracticeFormPage(driver);
  }

  public AlertsPage openAlerts() {
    openItem("Alerts");
    return new AlertsPage(driver);
  }

  public WebTablesPage openWebTables() {
    openItem("Web Tables");
    return new WebTablesPage(driver);
  }
}
