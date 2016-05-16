package lazadaOnlineShoppingTest;

import java.io.FileInputStream;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException; //Binary Interchange File Format basically excel file format
import localConstants.lct;
import localMethods.genericMethods;
import localVariables.lazadaVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

/**
 *  <h1>A Java class that will automatically shops online via sample URL(lazada) with the given values(items) at the .xls file using a registered account.</h1><br>
 * @version 1.1
 * @author K1el7
 *
 */
public class shopOnlineTestNG extends genericMethods{
	
	genericMethods gm = new genericMethods();
	lazadaVariables tvr = new lazadaVariables();
	
	
	/**
	 * <h1>First Test Method of the class</h1><br>
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @version 1.1
	 * @author K1el7
	 */
	@Test
	public void findAndShopItemOne() throws BiffException, IOException {
	
		gm.setUp("firefox","http://www.lazada.com.ph/");
		lazadaVariables tvr = new lazadaVariables();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='header__top']/div/ul/li[3]/span"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-popup-email"))).sendKeys(tvr.getMyLazadaUN());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-popup-pass"))).sendKeys(tvr.getMyLazadaPW());			
		driver.findElement(By.xpath("//*[@class='ui-formRow ui-submmit-field']/div[2]/button/span")).click();
		
		FileInputStream ShopList = new FileInputStream(tvr.getShopListDocumentPath());
		Workbook book1 = Workbook.getWorkbook(ShopList);
		Sheet Sheet1 = book1.getSheet(0);

		String item1 = Sheet1.getCell("B2").getContents();
				
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(lct.searchBox_id))).clear();
		gm.driver.findElement(By.id(lct.searchBox_id)).sendKeys(item1);
		gm.driver.findElement(By.className(lct.searchButton_classname)).click();
		
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		String item1Brand = Sheet1.getCell("C2").getContents();
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lct.brandFilter_xpath))).sendKeys(item1Brand);
		tvr.setFilterIndex(2);
		componentFinder("Brandname", "multiselect__content", item1Brand);
		
		tvr.setFilterIndex(3);
		String item1Color = Sheet1.getCell("D2").getContents();
		componentFinder("Color", "component component-color", item1Color);
		
		tvr.setFilterIndex(4);
		String item1Size = Sheet1.getCell("E2").getContents();
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lct.searchResult_xpath))).click();
		
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lct.variationsDropdown_xpath)));
		Select select = new Select(gm.driver.findElement(By.xpath(lct.variationsDropdown_xpath)));
		select.selectByVisibleText(item1Size);
		
		System.out.println("Final Prize "+ gm.driver.findElement(By.xpath(lct.finalPrize_xpath)).getText());

		gm.takeScreenShot(tvr.getAddedItemToCartPath(), item1);
		
		gm.driver.findElement(By.xpath(lct.addToCartButton_xpath)).click();
		gm.driver.findElement(By.id(lct.continueShoppingLink_id)).click();
	
	}
	/**
	 * <h1>Second Test Method of the class</h1><br>
	 * @throws BiffException
	 * @throws IOException
	 * @version 1.1
	 * @author K1el7
	 */
	@Test
	public void findAndShopItemTwo() throws BiffException, IOException{
		
		FileInputStream ShopList = new FileInputStream(tvr.getShopListDocumentPath());
		Workbook book1 = Workbook.getWorkbook(ShopList);
		Sheet Sheet1 = book1.getSheet(0);

		String item2 = Sheet1.getCell("B3").getContents();
				
		gm.driver.findElement(By.id(lct.searchBox_id)).clear();
		gm.driver.findElement(By.id(lct.searchBox_id)).sendKeys(item2);
		gm.driver.findElement(By.className(lct.searchButton_classname)).click();
		
		String item1Brand = Sheet1.getCell("C3").getContents();
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lct.brandFilter_xpath))).sendKeys(item1Brand);
		tvr.setFilterIndex(2);
		componentFinder("Brandname", "multiselect__content", item1Brand);
		
		tvr.setFilterIndex(3);
		String item1Size = Sheet1.getCell("E4").getContents();
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lct.searchResult_xpath))).click();
		
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lct.variationsDropdown_xpath)));
		Select select = new Select(gm.driver.findElement(By.xpath(lct.variationsDropdown_xpath)));
		select.selectByVisibleText(item1Size);
		
		System.out.println("Final Prize "+ gm.driver.findElement(By.xpath(lct.finalPrize_xpath)).getText());

		gm.takeScreenShot(tvr.getAddedItemToCartPath(), item2);
		
		gm.driver.findElement(By.xpath(lct.addToCartButton_xpath)).click();
		gm.driver.findElement(By.id(lct.continueShoppingLink_id)).click();
		
		gm.driver.quit();
		
		
	}
	
/**
 * <h1>A Method that will find the String component given to the parameters</h1><br>
 * @param component_type
 * @param component_feature
 * @version 1.1	
 * @author K1el7
 */
public void componentFinder(String component_specs, String component_type, String component_feature){
	int foundCounter = 1;
	Boolean found = false;
	while (found == false) {
		String component_idpath = "//*[@class='"+ component_type +"']/ul/li[" + foundCounter + "]";
		String component = gm.driver.findElement(By.xpath(component_idpath)).getAttribute("title");
		System.out.println(component);
		
		if (component_feature.equalsIgnoreCase(component)) {
			if(component_type.contains("color")){
				String foundComponent = component_idpath +"/a";
				gm.driver.findElement(By.xpath(foundComponent)).click();
			}else{
				String foundComponent = component_idpath +"/label";
				gm.driver.findElement(By.xpath(foundComponent)).click();
			}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			String filterHeader_xpath = "//*[@class='filter_state__filters']/div["+ tvr.getFilterIndex() +"]/span[2]";
			String filterHeader = gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(filterHeader_xpath))).getAttribute("innerHTML");
			try {
				assertTrue(component_feature.equalsIgnoreCase(filterHeader));
				System.out.println("Filtered Component's " + component_specs +" is "+filterHeader);
			} catch (AssertionError ae) {
				System.out.println("Filtered Component's " + component_specs +" is not"+ component_feature +"but " + filterHeader);
			}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			break;

		} else {
			foundCounter++;
		}
			
	}
}
}

