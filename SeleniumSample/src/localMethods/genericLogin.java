package localMethods;

import localVariables.lazadaVariables;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class genericLogin extends genericMethods{

	public void Login(){
		
		genericMethods gm = new genericMethods();
		lazadaVariables tvr = new lazadaVariables();
		
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='header__top']/div/ul/li[3]/span"))).click();
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-popup-email"))).sendKeys(tvr.getMyLazadaUN());
		gm.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-popup-pass"))).sendKeys(tvr.getMyLazadaPW());			
		gm.driver.findElement(By.xpath("//*[@class='ui-formRow ui-submmit-field']/div[2]/button/span")).click();
		
	}
}
