package Runner_Files;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features = "src\\main\\java\\Feature_Files\\Request_Second_Opinion.feature",
		glue= {"App_Script_Lan_wel_page"},
		monochrome = true,publish = true)

public class Request_Second_Opinion_Runner extends AbstractTestNGCucumberTests {

	

}
