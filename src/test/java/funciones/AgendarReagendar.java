package funciones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Util;

public class AgendarReagendar {
	
	public void agendar_reagendar (WebDriver driver, String fecha, String hora, String duracion) {
		
		Util util = new Util();
		
		
		String estado=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[4]/div/h6")).getText();
		util.waitSecods(1);
		
		
		
		if(estado.equals("Agendada no realizada")) {
			//hacemos click en el boton reagendar
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/div/div[2]/button/div/span")).click();
			util.waitSecods(1);
		}
		

		if(estado.equals("Sin agendar")) {
			//hacemos click en el boton agendar
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/div[1]/button/div/span")).click();
			util.waitSecods(2);
		}
		
		//Espera la carga del modal
		util.esperarElemento(driver, "//*[@id=\"modal-background\"]/div");
									
		//completamos la fecha
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div/div/div[1]/div/div[2]/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div/div/div[1]/div/div[2]/input")).sendKeys(fecha);
		util.waitSecods(1);
			
		//completamos la hora
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/input")).sendKeys(hora);
		util.waitSecods(1);
		
		if(duracion!="") {
			//completamos la duracion
			driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/input")).clear();
			driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/input")).sendKeys(duracion);
			util.waitSecods(1);
		}
		
		//hacemos click en el boton agendar visita
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[3]/div[2]/div/button")).click();
		util.waitSecods(2);
		
			
	}

}
