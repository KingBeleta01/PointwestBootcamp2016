package amazonTest;

import java.io.IOException;

import localConstants.amazonConstants;
import localMethods.genericMethods;
import localVariables.amazonVariables;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

/**
 * <h1>A class that test multiple scenarios using amazon as test environment.</h1><br>
 * 
 * @version 1.1
 * @author K1el7
 *
 */
public class amazon {

	genericMethods gm = new genericMethods();
	amazonConstants ac = new amazonConstants();
	amazonVariables av = new amazonVariables();
	
	@BeforeTest
	public void beforeTest() {
		gm.setUp("chrome",av.getAmazon_url());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>Check the Last Movie's title via "Quick Look"</h1><br>
	 * @version 1.1
	 */
	@Test
	public void test1_getLastImageURL() {

		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ac.primeMembershipMovie_id)));
		Assert.assertTrue(gm.driver.findElement(By.xpath(ac.primeMembershipMovie_id)).isDisplayed());
		
		Actions act = new Actions(gm.driver);
//		WebElement scrollBar = gm.driver.findElement(By.xpath(ac.pmmScrollbar_xpath));
//		WebElement scroll = gm.driver.findElement(By.xpath(ac.Scrollbar_xpath));
//		act.moveToElement(scrollBar).clickAndHold(scroll).moveByOffset(200, 0).build().perform();
		
		int lastMovieIndex = gm.driver.findElements(By.xpath(ac.movieDivs_id)).size();
		String lastMovie_xpath = ac.movieDivs_id+"["+lastMovieIndex+"]";
		WebElement lastElement = gm.driver.findElement(By.xpath(lastMovie_xpath));
		
		((JavascriptExecutor) gm.driver).executeScript("arguments[0].scrollIntoView(true);", lastElement);
		
		String quickLookUpButton_xpath = lastMovie_xpath+"/span[2]/span/input";
		act.moveToElement(lastElement).perform();
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(quickLookUpButton_xpath))).click();
		
		System.out.println(gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ac.quickLookPopUp_xpath))).getAttribute("src"));
			
			try {
				gm.takeScreenShot(av.getScreenshot_path(), "testScenario1_screenShot");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		gm.driver.findElement(ac.closeQuickLookPopUp_id).click();
		

	}
	
	/**
	 * <h1>Check if "selenium book" exist at Search Suggestion box using "selenium" as Search query</h1><br>
	 * @version 1.1
	 * 
	 */
//	@Test
	public void test2verifySearchQuery() {
		gm.driver.findElement(By.xpath(ac.searchHeader_xpath)).sendKeys(ac.searchQuery);
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ac.searchResultSuggestions_xpath)));

		int suggestionIndex = 0, counter = 1;
		suggestionIndex = gm.driver.findElements(By.xpath(ac.searchResultSuggestions_xpath)).size();
		String newSearchResultSuggestionCounter = ac.searchResultSuggestions_xpath+ "[" + counter + "]";

		for (counter = 1; counter < suggestionIndex; counter++) {
			String searchResult = gm.driver.findElement(By.xpath(newSearchResultSuggestionCounter)).getAttribute("data-keyword");
			if (searchResult.equalsIgnoreCase(ac.searchedItemName)) {
				Assert.assertEquals(searchResult, ac.searchedItemName);
				System.out.println(ac.searchedItemName + " was found in index "+ counter);
			} else {
				System.out.println(ac.searchedItemName + " was not in index "+ counter);
			}
		}
			try {
				gm.takeScreenShot(av.getScreenshot_path(), "testScenario2_screenShot");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Return the name of the item with the lowest price based on the Search querry at test2() method
	 * 
	 * @throws InterruptedException
	 * @version 1.1
	 */
//	@Test
	public void test3_returnLowestPriceItem() throws InterruptedException {

		gm.driver.findElement(By.xpath(ac.searchIcon_xpath)).click();
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ac.sorting_xpath)));
		new Select(gm.driver.findElement(By.xpath(ac.sorting_xpath))).selectByValue("price-asc-rank");
		Thread.sleep(5000);
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ac.firstSearchResult_xpath)));
		System.out.println(gm.driver.findElement(By.xpath(ac.firstSearchResult_xpath)).getText());
		try {
			gm.takeScreenShot(av.getScreenshot_path(), "testScenario3_screenShot");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		gm.tearDown();
	}

}
