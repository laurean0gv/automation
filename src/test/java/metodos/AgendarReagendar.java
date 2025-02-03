package metodos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Util;

public class AgendarReagendar {
	
	/**Funcion que sigue los pasos para agendar o reagendar una visita desde el front
	 * Recibe el driver, fecha para agendar/reagendar, la hora, la duracion y un estado
	 * Estados posibles: 1-Agendada | 2-Sin agendar
	**/
	public static void agendar_reagendar (WebDriver driver, String fecha, String hora, String duracion, Integer estado) {

		//Estados posibles: 1-Agendada | 2-Sin agendar
		
		//Espera la carga de la pantalla de detalles
		Util.esperarElemento(driver, "//h6[contains(text(),'ALQUIVIAL S R L')]");		
		Util.waitSecods(1);

		switch (estado) {
	    case 1: {
	        // Hacemos click en el botón reagendar
	        driver.findElement(By.xpath("//span[contains(text(),'Reagendar')]")).click();

	        // Espera la carga del modal
	        Util.esperarElemento(driver, "//div[contains(text(),' Reagendar visita')]");

	        // Completamos la fecha
	        driver.findElement(By.xpath("//input[contains(@placeholder,'--/--/----')]")).clear();
	        driver.findElement(By.xpath("//input[contains(@placeholder,'--/--/----')]")).sendKeys(fecha);
	        Util.waitSecods(0);

	        // Completamos la hora
	        driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).clear();
	        driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).sendKeys(hora);
	        Util.waitSecods(0);

	        // Completamos la duración
	        if (!duracion.isEmpty()) {
	            driver.findElement(By.xpath("(//input[contains(@placeholder,'--:--')])[2]")).clear();
	            driver.findElement(By.xpath("(//input[contains(@placeholder,'--:--')])[2]")).sendKeys(duracion);
	            Util.waitSecods(0);
	        }

	        // Hacemos click en el botón reagendar visita
	        driver.findElement(By.xpath("//span[contains(text(),'Reagendar visita')]")).click();
	        // util.waitSecods(2);
	        break;
	    }
	    case 2: {
	        // Hacemos click en el botón agendar
	        driver.findElement(By.xpath("//span[contains(text(),'Agendar')]")).click();

	        // Espera la carga del modal
	        Util.esperarElemento(driver, "//div[contains(text(),'Agendar visita')]");

	        // Completamos la fecha
	        driver.findElement(By.xpath("//input[contains(@placeholder,'--/--/----')]")).sendKeys(fecha);
	        Util.waitSecods(0);

	        // Completamos la hora
	        driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).clear();
	        driver.findElement(By.xpath("//input[contains(@placeholder,'--:--')]")).sendKeys(hora);
	        Util.waitSecods(0);

	        // Completamos la duración
	        if (!duracion.isEmpty()) {
	            driver.findElement(By.xpath("(//input[contains(@placeholder,'--:--')])[2]")).clear();
	            driver.findElement(By.xpath("(//input[contains(@placeholder,'--:--')])[2]")).sendKeys(duracion);
	            Util.waitSecods(0);
	        }

	        // Hacemos click en el botón agendar visita
	        driver.findElement(By.xpath("//span[contains(text(),'Agendar visita')]")).click();
	        // util.waitSecods(1);
	        break;
	    }
	    default: {
	        System.out.println("Estado no reconocido: " + estado);
	        break;
	    }
	}
		
			
	}

}
