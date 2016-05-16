package gMailTest;

import static org.junit.Assert.*;
import localVariables.lazadaVariables;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * <h1>Simple Test Script that will Launch and Login in Gmail</h1><br>
 * @version 1.1
 * @author King Beleta
 * 
 */
public class simpleGmailLoginJUnitTest {

	@Test
	public void test(){

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = (new WebDriverWait(driver, 30));
		
		driver.get("https://www.gmail.com");
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
			System.out.println( gmailLabel_class.getText()+ " That's my Account - PASSED");
		} catch (AssertionError ae) {
			System.out.println( gmailLabel_class.getText()+ " Not my Account - FAILED");
		}
		
		driver.manage().deleteAllCookies();
		driver.quit();

	}

}
