package org.automation.restassuredframework.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.automation.restassuredframework.hooks.CucumberHooks;
import org.automation.restassuredframework.models.request.CreateProductRequest;
import org.automation.restassuredframework.models.response.CreateProductResponse;
import org.automation.restassuredframework.services.ProductService;
import org.automation.restassuredframework.utils.AssertionUtils;
import org.testng.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateProductSteps {

  CreateProductRequest request;
  Response response;
  ProductService productService = new ProductService();

  @Given("user has valid product payload")
  public void preparePayload() {
    request = new CreateProductRequest();
    request.setTitle("Automation Product");
    request.setPrice(199.99);
    request.setDescription("Product created via automation");
    request.setImage("https://i.pravatar.cc");
    request.setCategory("electronics");
  }

  @When("user calls create product API")
  public void callCreateProductApi() {
    response = productService.createProduct(request, CucumberHooks.token);
  }

  @Then("verify status code should be {int}")
  public void verifyStatusCode(int expected) {
    AssertionUtils.verifyStatusCode(response, expected);
  }

  @Then("verify product response details")
  public void verifyResponse() {
    CreateProductResponse product =
        response.as(CreateProductResponse.class);

    Assert.assertNotNull(product.getId());
    Assert.assertEquals(product.getTitle(), request.getTitle());
    Assert.assertEquals(product.getCategory(), request.getCategory());
  }

  @And("response should match create product schema")
  public void responseShouldMatchCreateProductSchema() {
    AssertionUtils.verifyNotNull(response,"id");
    try {
      response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/create_product_schema.json"));
    } catch (AssertionError e) {
      Assert.fail("Response does not match create product schema: " + e.getMessage());
    }
  }
}
