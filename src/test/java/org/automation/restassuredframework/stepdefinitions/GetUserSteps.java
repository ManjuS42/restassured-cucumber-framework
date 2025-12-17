package org.automation.restassuredframework.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.automation.restassuredframework.services.UserService;
import org.automation.restassuredframework.utils.AssertionUtils;
import org.testng.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetUserSteps {

  Response response;
  UserService userService = new UserService();

  @When("user fetches users for page {int}")
  public void fetchUsers(int page) {
    response = userService.getUsers(page);
  }

  @Then("status code should be {int}")
  public void verifyStatusCode(int statusCode) {
    AssertionUtils.verifyStatusCode(response,statusCode);
  }

  @And("response should match get users schema")
  public void responseShouldMatchGetUsersSchema() {
    AssertionUtils.verifyNotNull(response,"email");
    try {
      response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/get_users_schema.json"));
    } catch (AssertionError e) {
      Assert.fail("Response does not match get users schema: " + e.getMessage());
    }
  }
}

