package funciones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Util;

public class DetalleVisita {
	
	public WebDriver accederDetalleVisita (WebDriver driver, String xpath, String nombre_empresa) {
		Util util = new Util();
				
		try {

			
			//buscamos la visita que queremos agendar
			driver.findElement(By.xpath(xpath)).click();
			util.waitSecods(1);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return driver;
		
	}

}
