package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"classpath:features/Posts.feature"},
		glue="steps",
		plugin= {"pretty",
				"html:target/cucumberreport.html"
		}
		)
public class RunPosts {

}
