package funciones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class LogOut {
	
		
	@Test
	public void logout(WebDriver driver) {
	
		
		driver.findElement(By.xpath("//span[contains(text(),'Mi perfil')]")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Cerrar sesión')]")).click();

		
	}
	
}
	