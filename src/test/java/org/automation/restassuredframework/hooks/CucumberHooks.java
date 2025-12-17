package org.automation.restassuredframework.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

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

