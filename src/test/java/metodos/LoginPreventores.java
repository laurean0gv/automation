package metodos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Util;

public class LoginPreventores {
	

	/**Funcion que sigue los pasos para hacer el login
	 * Recibe el driver, eñ usuario y la contraseña
	**/
	public static WebDriver login(WebDriver driver, String user, String pass) {

		//Espera la carga del login
		Util.esperarElemento(driver, "//h6[contains(text(),'Usuario')]");
		
		driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa tu nombre de usuario')]")).sendKeys(user);
	
		driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresá tu contraseña')]")).sendKeys(pass);
		
		driver.findElement(By.xpath("//span[contains(text(),'Iniciar sesión')]")).click();
		
		//Espera la carga de la tabla
		Util.esperarElemento(driver, "//span[contains(text(),'Mi perfil')]");

		return driver;
		
	}
	
}
	