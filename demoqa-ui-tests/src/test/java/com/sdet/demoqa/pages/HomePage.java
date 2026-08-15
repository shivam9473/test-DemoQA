package com.sdet.demoqa.pages;

import com.sdet.framework.config.Config;
import com.sdet.framework.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

  private final WebDriver driver;

  private static final By ELEMENTS_CARD = By.xpath("//div[@class='card mt-4 top-card'][.//h5[text()='Elements']]");
  private static final By FORMS_CARD = By.xpath("//div[@class='card mt-4 top-card'][.//h5[text()='Forms']]");
  private static final By ALERTS_CARD = By.xpath("//div[@class='card mt-4 top-card'][.//h5[text()='Alerts, Frame & Windows']]");
  private static final By WIDGETS_CARD = By.xpath("//div[@class='card mt-4 top-card'][.//h5[text()='Widgets']]");

  public HomePage(WebDriver driver) {
    this.driver = driver;
  }

  public HomePage open() {
    driver.get(Config.get("base.url", "https://demoqa.com"));
    return this;
  }

  public void openElementsSection() {
    Waits.click(driver, ELEMENTS_CARD);
  }

  public void openFormsSection() {
    Waits.click(driver, FORMS_CARD);
  }

  public void openAlertsSection() {
    Waits.click(driver, ALERTS_CARD);
  }

  public void openWidgetsSection() {
    Waits.click(driver, WIDGETS_CARD);
  }

  public SidebarMenu sidebar() {
    return new SidebarMenu(driver);
  }
}
