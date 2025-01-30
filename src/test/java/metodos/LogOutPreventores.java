package metodos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LogOutPreventores {
	
	/**Funcion que sigue los pasos para hacer el cierre de la sesion
	 * Recibe el driver
	**/
	public static void logout(WebDriver driver) {
	
		driver.findElement(By.xpath("//span[contains(text(),'Mi perfil')]")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Cerrar sesión')]")).click();
	
	}
	
}
	