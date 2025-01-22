package utils;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;

public class Util {
	

	/**Recibe en segundos el tiempo del sleep
	*waitSecods(int)
	**/
	public void waitSecods(int segundos) {
		try{
			Thread.sleep(segundos*1000);
			}
		catch(InterruptedException e){
			System.out.println(e);
			}
	}
	/**Recibe en segundos el tiempo del sleep y le agrega medio segundo mas
	*waitSecods(int)
	**/
	public void waitHalfSecods(int segundos) {
		try{
			Thread.sleep((segundos*1000)+500);
			}
		catch(InterruptedException e){
			System.out.println(e);
			}
	}
	
	/**Recibe el WebDriver, el xpath y los segundos a esperar y espera a que se cargue el elemento en pantalla
	*esperarElemento(WebDriver, String)
	**/
	public void esperarElemento(WebDriver driver, String ruta, int segundos) {
		Duration seg = Duration.ofSeconds(segundos);
		WebDriverWait wait = new WebDriverWait(driver, seg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ruta)));		
	}
	
	/**Recibe el WebDriver y el xpath y espera a que se cargue el elemento en pantalla
	*esperarElemento(WebDriver, String)
	**/
	public void esperarElemento(WebDriver driver, String ruta) {
		Duration seg = Duration.ofSeconds(60);
		WebDriverWait wait = new WebDriverWait(driver, seg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ruta)));		
	}
	
	/**Devuelve la fecha actual en formato dd/MM/YYYY
	*getDate()
	**/
	public String getDate() {		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**Recibe la cantidad de dias a sumar y devuelve la fecha actual mas los dias que se le pasaron en formato dd/MM/YYYY
	*getDate(int)
	**/
	public String getDate(int dias) {		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Date date = new Date();
		date.setDate(date.getDate() + dias);
		return dateFormat.format(date);
	}
	
	/**Reciber el webDrover y una descripcion para el nombre del archivo y Genera un screenshot de la pantalla
	*screenShot(WebDriver, String)
	**/
	public String screenShot (WebDriver driver, String descripcion) {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File ("error_"+descripcion+System.currentTimeMillis()+".png"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "error_"+descripcion+getDate()+".png";
				
	}
	
}
