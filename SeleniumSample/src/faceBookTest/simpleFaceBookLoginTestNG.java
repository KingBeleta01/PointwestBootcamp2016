package faceBookTest;

import java.util.concurrent.TimeUnit;
import localVariables.lazadaVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class simpleFaceBookLoginTestNG {
	
	public WebDriver driver;
	public WebDriverWait wait;

  @Test
  public void faceBookLogin_test() {
	  
	  wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("blueBarNAXAnchor")));
	  //String fb_xparth = "//*[@id='blueBarNAXAnchor']/div/div/div/div/h1/a/i";
	 
	  lazadaVariables tvr = new lazadaVariables();
	  driver.findElement(By.id("email")).sendKeys(tvr.getMyFBUN());
	  driver.findElement(By.id("pass")).sendKeys(tvr.getMyFBPW());
	  driver.findElement(By.id("loginbutton")).click();
	  
	  String nameBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fbxWelcomeBoxName"))).getText();
	  System.out.println(nameBox);
  }
	@BeforeMethod
	public void beforeMethod() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.facebook.com");
	}

	@AfterMethod
	public void afterMethod() {

		driver.manage().deleteAllCookies();
		driver.quit();

}
}