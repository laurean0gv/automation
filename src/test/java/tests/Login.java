package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import utils.Util;

public class Login {
	
	//WebDriver driver;
		
	
	@Test
	public void login(WebDriver driver, String user, String pass) {
		//driver=new ChromeDriver();
		Util util = new Util();
	
		util.waitSecods(2);
		
		
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[1]/div[2]/input")).sendKeys(user);
		
		util.waitSecods(1);
		
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[2]/div[2]/input")).sendKeys(pass);
		
		util.waitSecods(1);
		
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/button")).click();

		//return driver;
		
	}
	
}
	