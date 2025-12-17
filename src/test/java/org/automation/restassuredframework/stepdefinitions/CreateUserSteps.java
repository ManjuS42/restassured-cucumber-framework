package org.automation.restassuredframework.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.automation.restassuredframework.models.request.CreateUserRequest;
import org.automation.restassuredframework.services.UserService;
import org.automation.restassuredframework.utils.AssertionUtils;
import org.testng.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateUserSteps {

  CreateUserRequest request;
  Response response;
  UserService userService = new UserService();

  @Given("user has valid user creation payload")
  public void preparePayload() {
    request = new CreateUserRequest();
    request.setEmail("test@mail.com");
    request.setUsername("manju_user");
    request.setPassword("password123");
  }

  @When("user calls create user API")
  public void callCreateUser() {
    response = userService.createUser(request);
  }

  @And("user id should be generated")
  public void verifyUserId() {
    int id = response.jsonPath().getInt("id");
    Assert.assertTrue(id > 0);
  }

  @And("response should match with user creation schema")
  public void responseShouldMatchWithUserCreationSchema() {
    AssertionUtils.verifyNotNull(response,"id");
    try {
      response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/create_user_schema.json"));
    } catch (AssertionError e) {
      Assert.fail("Response does not match user creation schema: " + e.getMessage());
    }
  }

  @Then("status code from response should be {int}")
  public void statusCodeFromResponseShouldBe(int statusCode) {
    AssertionUtils.verifyStatusCode(response,statusCode);
  }

}

