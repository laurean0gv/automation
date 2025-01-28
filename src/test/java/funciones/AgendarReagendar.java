package funciones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Util;

public class AgendarReagendar {
	
	public void agendar_reagendar (WebDriver driver, String fecha, String hora, String duracion, Integer estado) {

		Util util = new Util();
		String visitaAgendada="Agendada no realizada";
		String visitaSinAgendar="Sin agendar";
		//Estados posibles: 1-Agendada | 2-Sin agendar
		
		//Espera la carga de la pantalla de detalles
		util.esperarElemento(driver, "//h6[contains(text(),'ALQUIVIAL S R L')]");		
		util.waitSecods(1);

		switch(estado) {
			case 2:{
				//hacemos click en el boton agendar
				driver.findElement(By.xpath("//span[contains(text(),'Agendar')]")).click();
				
				//Espera la carga del modal
				util.esperarElemento(driver, "//div[contains(text(),'Agendar visita')]");
				
				//completamos la fecha
				driver.findElement(By.xpath("//input[contains(@placeholder,'--/--/----')]")).sendKeys(fecha);
				util.waitSecods(0);
				
				//completamos la hora
				driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).sendKeys(hora);
				util.waitSecods(0);
				
				//completamos la hora
				driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).clear();
				driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).sendKeys(hora);
				util.waitSecods(0);
				
				//cpompletamos la duracion
				if(duracion!="") {
					//completamos la duracion
					driver.findElement(By.xpath("(//input[contains(@placeholder,'--:--')])[2]")).clear();
					driver.findElement(By.xpath("(//input[contains(@placeholder,'--:--')])[2]")).sendKeys(duracion);
					util.waitSecods(0);
				}
				
				//hacemos click en el boton agendar visita
				driver.findElement(By.xpath("//span[contains(text(),'Agendar visita')]")).click();
				//util.waitSecods(1);
			}
			case 1:{
				//hacemos click en el boton reagendar
				driver.findElement(By.xpath("//span[contains(text(),'Reagendar')]")).click();
				
				//Espera la carga del modal
				util.esperarElemento(driver, "//div[contains(text(),' Reagendar visita')]");
				
				//completamos la fecha
				driver.findElement(By.xpath("//input[contains(@placeholder,'--/--/----')]")).clear();
				driver.findElement(By.xpath("//input[contains(@placeholder,'--/--/----')]")).sendKeys(fecha);
				util.waitSecods(0);
				
				//completamos la hora
				driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).clear();
				driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).sendKeys(hora);
				util.waitSecods(0);
				
				//completamos la hora
				driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).clear();
				driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).sendKeys(hora);
				util.waitSecods(0);
				
				//cpompletamos la duracion
				if(duracion!="") {
					//completamos la duracion
					driver.findElement(By.xpath("(//input[contains(@placeholder,'--:--')])[2]")).clear();
					driver.findElement(By.xpath("(//input[contains(@placeholder,'--:--')])[2]")).sendKeys(duracion);
					util.waitSecods(0);
				}
				
				//hacemos click en el boton agendar visita
				driver.findElement(By.xpath("//span[contains(text(),'Reagendar visita')]")).click();
				//util.waitSecods(2);
			}
		}
		
			
	}

}
