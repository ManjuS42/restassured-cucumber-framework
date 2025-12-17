package org.automation.restassuredframework.base;

import org.automation.restassuredframework.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.automation.restassuredframework.utils.ConfigReader;

public class BaseService {

  protected RequestSpecification request;
  protected Response response;

  public BaseService() {
    request = RestAssured.given()
        .baseUri(ConfigReader.get("base.uri"))
        .header("User-Agent", ConfigReader.get("user.agent"))
        .contentType(ContentType.JSON)
        .log().all();
  }

  protected Response get(String endpoint) {
    response = request.get(endpoint);
    response.then().log().all();
    return response;
  }

  protected Response post(String endpoint, Object payload) {
    response = request.body(payload).post(endpoint);
    response.then().log().all();
    return response;
  }

  protected void setAuthToken(String token) {
    request.header("Authorization", "Bearer " + token);
  }

}

