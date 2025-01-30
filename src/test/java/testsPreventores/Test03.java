package tests;
import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Configuracion;
import utils.Util;

public class Test03 {
	/**
	 * Test para vista mobile
	 */
	private static final String empresa=Configuracion.getPropiedad("Test03","EMPRESA");
	private static final String contrato=Configuracion.getPropiedad("Test03","CONTRATO");
	private static final String prioridad=Configuracion.getPropiedad("Test03","PRIORIDAD");
	private static final String calle=Configuracion.getPropiedad("Test03","CALLE");
	private static final String provincia=Configuracion.getPropiedad("Test03","PROVINCIA");
	
	WebDriver driver;
		
	
	@BeforeMethod
	public void setUp() {
		System.getProperty("wedriver.chrome.driver", "/AutomationTestPrevencion/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://desa-preventores.artprovincia.ar/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
	}
	
	@Test
	public void testCase() {
		Util util = new Util();
		
		util.waitSecods(2);

		//busca el mes abril 2024
		driver.findElement(By.cssSelector(".material-symbols-outlined.content-center.text-h6")).click();
				
		while(!Objects.equals(driver.findElement(By.cssSelector(".typography-button2.p-2.text-primary-10.capitalize")).getText(),"Septiembre 2024")) {
			driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div[1]/button")).click();
			util.waitHalfSecods(0);
		}
		
		
		//busca el 04 de abril
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div[2]/div/div[2]/div/div/button[4]/div[2]/div")).click();
		util.waitSecods(1);
		
		//obtiene en la agenda el nombre de la empresa
		String empresaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[2]/div/div[1]")).getText();
		//obtiene en la agenda la hora
		String contratoObtenido=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[4]/div/div")).getText();
		//obtiene en la agenda la prioridad
		String prioridadObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[9]/div/div/div/div/div/div")).getText();
		//obtiene en la agenda la direccion
		String calleObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[11]/div/div[1]")).getText();
		String provinciaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[12]/div/div[1]")).getText();
		
		util.waitSecods(1);
				
		//compara los elementos de la card y validamos con los de referencia para dar por valido el caso
		//si el if no se cumple, el caso falla
		if(Objects.equals(empresa,empresaObtenida) &&  Objects.equals(contrato,contratoObtenido) &&  Objects.equals(prioridad,prioridadObtenida) 
				&& Objects.equals(calle,calleObtenida) && Objects.equals(provincia,provinciaObtenida)) {
			System.out.println("Pass");
		}
		else {
			Assert.fail();
		}
		
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
	
}
	