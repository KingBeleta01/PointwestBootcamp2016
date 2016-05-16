package youTubeTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class simpleMovieSearchTestNG {
	
	public WebDriver driver;
	
  @Test
  public void youtube_test() {
	 driver.findElement(By.id("masthead-search-term")).sendKeys("Age of Ultron Trailer");
	 driver.findElement(By.id("search-btn")).click();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 
	 String movie_linkText = "OFFICIAL";
	 //blob:https%3A//www.youtube.com/131fa431-e72e-4e77-9399-c5fbe172a952
	 //blob:https%3A//www.youtube.com/116191bb-ba7a-45f1-b62f-b5db62c1c85e
	 driver.findElement(By.partialLinkText(movie_linkText)).click();
	 try {
		Thread.sleep(150000); // 2.5secs
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	 driver.findElement(By.id("movie_player")).click();
  }
	@BeforeMethod
	public void beforeMethod() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://www.youtube.com");
	}

	@AfterMethod
	public void afterMethod() {

		driver.manage().deleteAllCookies();
		driver.quit();

}
}