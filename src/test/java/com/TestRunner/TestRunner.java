package com.TestRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/Tajalwal.html",
                "rerun:target/rerun.txt"},
        features = "src/test/resources/features",
        glue = "StepDefinition",
        dryRun = false,
        tags = {"@Smoke"})

public class TestRunner {

}
