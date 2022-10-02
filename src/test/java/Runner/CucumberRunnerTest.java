package Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@AddEmployee",
        features = "src/test/features/",
        glue ={"stepDefinitions"}
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

}
