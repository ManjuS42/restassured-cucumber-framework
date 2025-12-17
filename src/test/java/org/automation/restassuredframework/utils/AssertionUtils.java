package org.automation.restassuredframework.utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class AssertionUtils {

  public static void verifyStatusCode(Response response, int expected) {
    Assert.assertEquals(response.getStatusCode(), expected);
  }

  public static void verifyNotNull(Response response, String jsonPath) {
    Assert.assertNotNull(response.jsonPath().get(jsonPath));
  }
}
