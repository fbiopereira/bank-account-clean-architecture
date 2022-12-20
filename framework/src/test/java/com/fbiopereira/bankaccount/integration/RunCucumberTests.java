package com.fbiopereira.bankaccount.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest
@CucumberOptions(features = "src/test/resources", plugin = {"pretty", "json:target/cucumber-report.json"})
class RunCucumberTests {

}
