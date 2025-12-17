package org.automation.restassuredframework.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.automation.restassuredframework.models.request.LoginRequest;
import org.automation.restassuredframework.services.AuthService;

public class CucumberHooks {

  public static String token;

  @Before("@Authentication")
  public void generateToken() {
    LoginRequest request = new LoginRequest();
    request.setUsername("mor_2314");
    request.setPassword("83r5^_");

    Response response = new AuthService().login(request);
    token = response.jsonPath().getString("token");
    System.out.println("Generated Token: " + token);
  }

  @Before
  public void beforeScenario(Scenario scenario) {
    System.out.println("=================================================");
    System.out.println("Starting Scenario: " + scenario.getName());
    System.out.println("=================================================");
  }

  @After
  public void afterScenario(Scenario scenario) {
    System.out.println("=================================================");
    System.out.println("Finished Scenario: " + scenario.getName());
    System.out.println("Status: " + scenario.getStatus());
    System.out.println("=================================================");
  }
}

