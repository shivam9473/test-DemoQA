package com.sdet.framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

  private int attempt = 0;
  private static final int MAX = 1;

  @Override
  public boolean retry(ITestResult result) {
    if (attempt < MAX) {
      attempt++;
      return true;
    }
    return false;
  }
}
