package org.automation.restassuredframework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "@target/failed_scenarios.txt",
    glue = {"org.automation.restassuredframework.stepdefinitions", "org.automation.restassuredframework.hooks"},

    plugin = {"pretty", "html:target/cucumber-reports/failed-cucumber.html",
        "json:target/cucumber-reports/failed-cucumber.json"}, monochrome = true)

public class FailedCucumberRunner extends AbstractTestNGCucumberTests {
}
