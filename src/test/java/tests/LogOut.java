package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class LogOut {
	
		
	@Test
	public void logout(WebDriver driver) {
	
		
		driver.findElement(By.xpath("/html/body/div/div/header/div/nav/ul/li/a")).click();

	
		
	}
	
}
	