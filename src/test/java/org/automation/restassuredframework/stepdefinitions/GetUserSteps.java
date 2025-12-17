package org.automation.restassuredframework.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.automation.restassuredframework.services.UserService;
import org.testng.Assert;

public class GetUserSteps {

  Response response;
  UserService userService = new UserService();

  @When("user fetches users for page {int}")
  public void fetchUsers(int page) {
    response = userService.getUsers(page);
  }

  @Then("status code should be {int}")
  public void verifyStatusCode(int statusCode) {
    Assert.assertEquals(response.getStatusCode(), statusCode);
  }
}

