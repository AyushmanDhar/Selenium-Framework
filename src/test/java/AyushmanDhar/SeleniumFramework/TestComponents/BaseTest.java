package AyushmanDhar.SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AyushmanDhar.SeleniumFramework.pages.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	public String baseURL;
	public WebDriver initializeDriver() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//AyushmanDhar//SeleniumFramework//Resources//GlobalData.properties");
		prop.load(fis);
		
		//Takes Browser value from command line if there is none take from properties file then create driver accordingly
		
		String browser=System.getProperty("Browser")!=null ? System.getProperty("Browser") : prop.getProperty("Browser");
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		if(browser.equals("edge")) {
			driver=new EdgeDriver();
		}
		
		//Takes environment value from command line if there is none take from properties file
		
		String environment=System.getProperty("env")!=null ? System.getProperty("env") : prop.getProperty("env");
		if(environment.equals("prod")) {
			baseURL="https://rahulshettyacademy.com/client";
		}
		if(environment.equals("qa")) {
			baseURL="https://rahulshettyacademy.com/client";
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	
	public void launchURL() {
		driver.get(baseURL);
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		launchURL();
		lp=new LandingPage(driver);
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void driverQuit() {
		driver.quit();
	}
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return (System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
	}
}