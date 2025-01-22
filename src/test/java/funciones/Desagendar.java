package funciones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Util;

public class Desagendar {
	
	public void desagendar (WebDriver driver) {
		
		Util util = new Util();
		

		//hacemos click en el boton desagendar
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/div/div[1]/button/div/span")).click();
		util.waitSecods(1);
		

		//Espera la carga del modal
		util.esperarElemento(driver, "//*[@id=\"modal-background\"]/div");
									
		
		//hacemos click en el boton desagendar visita
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[3]/button[2]/div/span")).click();
		util.waitSecods(2);
		
			
	}

}
