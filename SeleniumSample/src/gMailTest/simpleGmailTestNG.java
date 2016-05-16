package gMailTest;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import localVariables.lazadaVariables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class simpleGmailTestNG {

	public WebDriver driver;
	public WebDriverWait wait;

	@Test
	public void gmail_test() {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='banner']/h2")));

		lazadaVariables tvr = new lazadaVariables();
		driver.findElement(By.id("Email")).sendKeys(tvr.getMyUsername());
		driver.findElement(By.id("Passwd")).sendKeys(tvr.getMyPassword());
		driver.findElement(By.id("signIn")).click();

		String gmailLabel_xpath = "//*[@class='gb_m gb_o']";
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(gmailLabel_xpath)));

		WebElement gmailLabel_class = driver.findElement(By.xpath(gmailLabel_xpath));

		try {
			assertTrue("Assert if the account is mine.", gmailLabel_class.getText().contains("King"));
			System.out.println(gmailLabel_class.getText()+ " That's my Account");
		} catch (AssertionError ae) {
			System.out.println(gmailLabel_class.getText() + " Not my Account");
		}

		String inbox_xpath = "//*[@class='TK']/div/div/div/div/span/a";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(inbox_xpath))).click();
		
		int foundCounter = 1;
		Boolean found = false;
		while (found == false) {
			String emailIndex_xpath = "//*[@class='aDP']/div/div[2]/div/table/tbody/tr[" + foundCounter + "]/td[5]/div[2]/span";
			String emailName = driver.findElement(By.xpath(emailIndex_xpath)).getText();
			System.out.println(emailName);
			if ("Jech Valeros".contains(emailName)) {
				driver.findElement(By.xpath(emailIndex_xpath)).click();

				String emailheader_xpath = "//*[@class='nH']/div/h2";
				String emailHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailheader_xpath))).getText();
				try {
					assertTrue("Interview Details".equalsIgnoreCase(emailHeader));
					System.out.println("This Email is From " + emailName + " about: " + emailHeader);
				} catch (AssertionError ae) {
					System.out.println("This Email is From " + emailName + "But not about Interview Details");
				}
				break;

			} else {
				foundCounter++;
			}
		}

		driver.findElement(
				By.xpath("//*[@class='gb_Lc gb_1c gb_o']/div[5]/div/a")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='gb_Ba']/div[2]/a"))).click();
	}

	@BeforeMethod
	public void beforeMethod() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.gmail.com");
		

	}

	@AfterMethod
	public void afterMethod() {

		driver.manage().deleteAllCookies();
		driver.quit();

	}

}
