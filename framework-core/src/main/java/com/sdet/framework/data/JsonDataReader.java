package com.sdet.framework.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class JsonDataReader {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private JsonDataReader() {}

  public static List<Map<String, String>> readRows(String resourcePath) {
    try (InputStream in = JsonDataReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
      if (in == null) {
        throw new IllegalArgumentException("Resource not found: " + resourcePath);
      }
      return MAPPER.readValue(in, new TypeReference<>() {});
    } catch (IOException e) {
      throw new IllegalStateException("Could not read " + resourcePath, e);
    }
  }
}
