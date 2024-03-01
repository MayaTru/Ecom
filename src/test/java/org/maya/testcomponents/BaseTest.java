package org.maya.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.maya.ecomnew.LandingPage;
import org.maya.testdata.DataReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	public WebDriver driver;
	
	public LandingPage obj1;
	
	public WebDriver initdriver() throws IOException {
		Properties prop = new Properties();
		System.out.println(System.getProperty("user.dir"));
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\maya\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("firefox")) {
			//Initialize Firefox driver
			System.setProperty("webdriver.gecko.driver", "C:\\JarsForTestAut\\firefoxdriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\JarsForTestAut\\edgedriver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}else if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\JarsForTestAut\\driver6\\chromedriver.exe");
			ChromeOptions opt = new ChromeOptions();
			if(browserName.contains("headless")) {
				opt.addArguments("headless");
			}
			opt.addArguments("--remote-allow-origins=*");
			opt.setBinary("C:\\JarsForTestAut\\chrome-win64\\chrome-win64\\chrome.exe");
			driver = new ChromeDriver(opt);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	public LandingPage launchApp(String url) throws IOException {
		driver = initdriver();
		LandingPage obj1 = new LandingPage(driver);
		obj1.GoTo(url);
		return obj1;
	}
	@AfterMethod(alwaysRun = true)
	public void closeBrowserSession() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
	
	public List<HashMap<String, String>> initData(String path) throws IOException {
		DataReader d1 = new DataReader();
		List<HashMap<String, String>> dataitr = d1.getdatafromfile(path);
		return dataitr;
	}
	
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		String pathtoss = System.getProperty("user.dir")+"\\reports"+ testcasename +"\\index.html";
		TakesScreenshot ss = (TakesScreenshot)driver;
		File source = ss.getScreenshotAs(OutputType.FILE);
		File takenss = new File(pathtoss);
		FileUtils.copyFile(source, takenss);
		return System.getProperty("user.dir")+"\\reports"+ testcasename +"\\index.html";
	}
}
