package org.automation.restassuredframework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
    glue = {"org.automation.restassuredframework.stepdefinitions", "org.automation.restassuredframework.hooks"},

    tags = "@Sanity",
    plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json",
        "rerun:target/failed_scenarios.txt"}, monochrome = true)

public class CucumberRunner extends AbstractTestNGCucumberTests {
}
