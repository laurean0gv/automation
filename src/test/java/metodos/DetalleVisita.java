package metodos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Util;

public class DetalleVisita {
	
	/**Funcion que sigue los pasos para acceder al detalle de una viista
	 * Recibe el driver en el moento que esta en la tabla de visita, el xpath y el nombre de la empresa
	**/
	
	//FALTA TERMINAR, TODAVIA SIN USO
	public static WebDriver accederDetalleVisita (WebDriver driver, String xpath, String nombre_empresa) {
		
		try {
			//buscamos la visita que queremos agendar
			driver.findElement(By.xpath(xpath)).click();
			Util.waitSecods(1);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return driver;
		
	}

}
