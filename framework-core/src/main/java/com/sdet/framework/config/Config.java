package com.sdet.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

  private static final Properties PROPS = new Properties();

  static {
    load("config.properties");
    String env = System.getProperty("env", PROPS.getProperty("env", "local"));
    load("config-" + env + ".properties");
  }

  private Config() {}

  private static void load(String file) {
    try (InputStream in = Config.class.getClassLoader().getResourceAsStream(file)) {
      if (in != null) {
        PROPS.load(in);
      }
    } catch (IOException e) {
      throw new IllegalStateException("Could not load " + file, e);
    }
  }

  public static String get(String key) {
    return firstNonBlank(System.getProperty(key), PROPS.getProperty(key));
  }

  public static String get(String key, String defaultValue) {
    String value = get(key);
    return value != null ? value : defaultValue;
  }

  public static boolean bool(String key, boolean defaultValue) {
    String value = get(key);
    return value == null ? defaultValue : Boolean.parseBoolean(value);
  }

  public static int integer(String key, int defaultValue) {
    String value = get(key);
    return value == null ? defaultValue : Integer.parseInt(value);
  }

  private static String firstNonBlank(String a, String b) {
    if (a != null && !a.isBlank()) {
      return a;
    }
    return b;
  }
}
