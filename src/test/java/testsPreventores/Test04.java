package tests;


import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import backend.DeleteDesagendar;
import backend.PostLogin;
import funciones.LogOut;
import funciones.Login;
import utils.Configuracion;
import utils.Util;


public class Test04 {
	/**
	 * Test para vista mobile
	 */
	private static final String user=Configuracion.getPropiedad("Test04","USER");
	private static final String pass=Configuracion.getPropiedad("Test04","PASS");
	private static final String empresa=Configuracion.getPropiedad("Test04","EMPRESA");
	private static final String contrato=Configuracion.getPropiedad("Test04","CONTRATO");
	private static final String prioridad=Configuracion.getPropiedad("Test04","PRIORIDAD");
	private static final String calle=Configuracion.getPropiedad("Test04","CALLE");
	private static final String provincia=Configuracion.getPropiedad("Test04","PROVINCIA");
	
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
		Login login = new Login();
		LogOut logout = new LogOut();
		PostLogin postLogin = new PostLogin();
		DeleteDesagendar delete = new DeleteDesagendar();
		

		//String token =PostLogin.loginBack();
		
		//delete.loginBack(token,"4003550");
		util.waitSecods(2);
		
		//Hacemos login
		login.login(driver, user, pass);
		util.waitSecods(5);
		
		//buscamos la visita que queremos agendar
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div/div/table/tbody/tr[10]/td[2]/div/div/a")).click();
		util.waitSecods(1);

		//hacemos click en el boton agendar
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/button/div/span")).click();
		util.waitSecods(1);
		
		//completamos la fecha
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div/div/div[1]/div/div[2]/input")).sendKeys("10102025");
		util.waitSecods(1);
		
		//completamos la fecha
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div/div/div[2]/div[2]/div[1]/input")).sendKeys("1000");
		util.waitSecods(1);
		
		//hacemos click en el boton agendar visita
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[3]/div[2]/div/button/div/span")).click();
		util.waitSecods(2);
		
		//obtiene en la agenda el nombre de la empresa
		String estadoViistaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[4]/div/h6")).getText();
		
		//hacemos el logout
		logout.logout(driver);
		
		if(Objects.equals("Agendada no realizada",estadoViistaObtenida)) {
			System.out.println("Pass");
		}
		else {
			Assert.fail();
		}

        
        
		/*
		//busca el mes abril 2024
		driver.findElement(By.cssSelector(".material-symbols-outlined.content-center.text-h6")).click();
				
		while(!Objects.equals(driver.findElement(By.cssSelector(".typography-button2.p-2.text-primary-10.capitalize")).getText(),"Septiembre 2024")) {
			driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div[1]/button")).click();
			util.waitHalfSecods(0);
		}
		
		
		/*
		//busca el 04 de abril
		driver.findElement(By.xpath("//*[@id=\"modal-background\"]/div/div/div[2]/div[2]/div/div[2]/div/div/button[4]/div[2]/div")).click();
		util.waitSecods(1);
		/*
		//obtiene en la agenda el nombre de la empresa
		String empresaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[2]/div/div[1]")).getText();
		//obtiene en la agenda la hora
		String contratoObtenido=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[4]/div/div")).getText();
		//obtiene en la agenda la prioridad
		String prioridadObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[9]/div/div/div/div/div/div")).getText();
		//obtiene en la agenda la direccion
		String calleObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[11]/div/div[1]")).getText();
		String provinciaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[4]/div/div/table/tbody/tr/td[12]/div/div[1]")).getText();
		*/
		/*util.waitSecods(1);
		
		logout.logout(driver);
		
		util.waitSecods(5);
			/*	
		//compara los elementos de la card y validamos con los de referencia para dar por valido el caso
		//si el if no se cumple, el caso falla
		if(Objects.equals(empresa,empresaObtenida) &&  Objects.equals(contrato,contratoObtenido) &&  Objects.equals(prioridad,prioridadObtenida) 
				&& Objects.equals(calle,calleObtenida) && Objects.equals(provincia,provinciaObtenida)) {
			System.out.println("Pass");
		}
		else {
			Assert.fail();
		}*/
		
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
	
}
	