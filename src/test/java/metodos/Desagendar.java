package metodos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Util;

public class Desagendar {
	
	/**Funcion que sigue los pasos para desagendar una visita desde el front
	 * Recibe el driver en el moento que ya esta posicionado sobre la visita que deseamos desagendar
	**/
	public static void desagendar (WebDriver driver) {

		//hacemos click en el boton desagendar
		driver.findElement(By.xpath("//span[contains(text(),'Desagendar')]")).click();
		Util.waitSecods(1);
		

		//Espera la carga del modal
		Util.esperarElemento(driver, "//*[@id=\"modal-background\"]/div");
									
		
		//hacemos click en el boton desagendar visita
		driver.findElement(By.xpath("(//span[contains(text(),'Desagendar')])[2]")).click();
		Util.waitSecods(2);
		
			
	}

}
