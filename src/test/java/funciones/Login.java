package funciones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import utils.Util;

public class Login {
	
	//WebDriver driver;
		
	
	@Test
	public WebDriver login(WebDriver driver, String user, String pass) {
		Util util = new Util();
	
		util.waitSecods(1);
		
		
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[1]/div[2]/input")).sendKeys(user);
		
		util.waitSecods(1);
		
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[2]/div[2]/input")).sendKeys(pass);
		
		util.waitSecods(1);
		
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/button")).click();
		
		//Espera la carga de la tabla
		util.esperarElemento(driver, "//*[@id=\"root\"]/div/div[1]/div/div[4]");

		return driver;
		
	}
	
}
	