package auto;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class ExtentReportExample {

	@Test
	
	public void test1(){
		
		System.setProperty("webdriver.gecko.driver", "./software/geckodriver.exe");
		
		WebDriver driver=new FirefoxDriver();
		
		//driver.get("https://www.selenium.org/");
		
		//1. create an object of extent report
		
		ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(new File("C:\\Users\\Mona\\Desktop\\reports\\extentreport1.html"));

		//2. create an object of extentreports class attach report and create a test
		
		//creating the document name and the report name
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Test Yantra");
		htmlReporter.config().setReportName("Regression Test Suite");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("Engineer name", "mona");
		extent.setSystemInfo("Build no", "1.2");
		extent.setSystemInfo("platform", "windows");
		
		//3. create a test 
		ExtentTest test=extent.createTest("Home Page Test");
		
		//4. log all the important test
		driver.get("https://www.seleniumhq.org/");
		
		String actualTitle=driver.getTitle();
		String expectedTitle="Selenium - Web Browser Automation";
		try
		{
			Assert.assertEquals(actualTitle, expectedTitle);
			test.log(Status.PASS, "Navigated to the target URL");
			TakesScreenshot ts=(TakesScreenshot)driver;
			File fsrc=ts.getScreenshotAs(OutputType.FILE);
			File dsc=new File("C:\\Users\\Mona\\eclipse-workspace\\Photon\\Selenium1\\screenshots.selenium.png");
			Files.copy(fsrc, dsc);
			
			//5. attaching screen shot
			test.addScreenCaptureFromPath("C:\\Users\\Mona\\eclipse-workspace\\Photon\\Selenium1\\screenshots\\.selenium.png");
		}
		catch(Exception e) {
			test.log(Status.FAIL, "target url is not working");
		}
		
		driver.findElement(By.xpath("//a[@title='Get Selenium']")).click();
		
		//6. write the log details to the html file
		
		driver.close();
		extent.flush();
		
			}

}
