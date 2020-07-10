package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/feature" , plugin="json:target/jsonReports/cucumber-report.json", glue= {"stepDefinitions"})
public class TestRunner {
	//tags=@AddPlace


}

//In commandline , to run the mevan test
//mvn test
//to run with tags
//mvn test -Dcucumber.options="--tags @smoke"