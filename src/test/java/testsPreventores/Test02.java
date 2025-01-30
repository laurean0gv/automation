package tests;
import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Configuracion;
import utils.Util;

public class Test02 {
	/**
	 * Test para vista mobile
	 */
	private static final String empresa=Configuracion.getPropiedad("Test02","EMPRESA");
	private static final String hora=Configuracion.getPropiedad("Test02","HORA");
	private static final String prioridad=Configuracion.getPropiedad("Test02","PRIORIDAD");
	private static final String calle=Configuracion.getPropiedad("Test02","CALLE");
	private static final String provincia=Configuracion.getPropiedad("Test02","PROVINCIA");
	
	WebDriver driver;
		
	
	@BeforeMethod
	public void setUp() {
		System.getProperty("wedriver.chrome.driver", "/AutomationTestPrevencion/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		//driver.manage().window().maximize();
		driver.get("https://desa-preventores.artprovincia.ar/");
		driver.manage().window().setSize(new Dimension(400, 1000));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@Test
	public void testCase() {
		Util util = new Util();
		
		util.waitSecods(2);

		//busca el mes abril 2024
			while(!Objects.equals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/div[1]/button/div/div")).getText(),"Abril 2024")) {
				driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/div[2]/div[1]/button/div")).click();
				util.waitHalfSecods(0);
			}
			
		//busca el 23 de abril
	
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/div[2]/div[2]/div/div[2]/div/div/button[24]")).click();
		util.waitSecods(1);

		//obtiene en la agenda el nombre de la empresa
		String empresaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div[1]/div[1]")).getText();
		//obtiene en la agenda la hora
		String horaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div[1]/div[2]")).getText();
		//obtiene en la agenda la prioridad
		String prioridadObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/div[2]")).getText();
		//obtiene en la agenda la direccion
		String calleObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
		String provinciaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[2]/div[2]")).getText();
		
		util.waitSecods(1);

		
		//compara los elementos de la card y validamos con los de referencia para dar por valido el caso
		//si el if no se cumple, el caso falla
		if(Objects.equals(empresa,empresaObtenida) && Objects.equals(hora,horaObtenida) && Objects.equals(prioridad,prioridadObtenida) 
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
	