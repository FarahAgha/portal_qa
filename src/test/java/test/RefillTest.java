package test;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue ="patientportal",
        features = "classpath:patientportal",
        plugin = {"pretty", "html:target/cucumber-html-report","json:target/cucumber/Login.json"},
        tags = "@refill"
)

public class RefillTest {
}
