package localMethods;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class genericMethods{
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	/**
	 * <h1>A Method that will initiate the driver, the wait and the window properties, then navigates to given url</h1><br>
	 * @param url
	 */
	public void setUp(String browser, String url) {
		
		String dir = System.getProperty("user.dir");
		
		if (null != browser && browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		if (null != browser && browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", dir+"/driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (null != browser && browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", dir+"/driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
		driver.get(url);

	}

	/**
	 * <h1>A Method that will delete all Browser Cookies and definitely Will Close and End the Test</h1><br>
	 */
	public void tearDown(){
		driver.manage().deleteAllCookies();
		driver.close();
	}
	
	/**
	 * <h1>A Method that will take a screenshot and saved it as a .jpg file at the workplace</h1><br>
	 * @param path
	 * @param itemName
	 * @throws IOException
	 */
	public void takeScreenShot(String path, String itemName) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(path+ itemName +".jpg"));
	}
}
