package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import backend.Azure;
import backend.PostAgendar;
import backend.PostLogin;
import funciones.LogOut;
import funciones.Login;
import funciones.Desagendar;
import funciones.DetalleVisita;
import utils.Configuracion;
import utils.Util;

public class Test_DesagendarVisita {

	private static final String user=Configuracion.getPropiedad("Test_ReagendarVisita","USER");
	private static final String pass=Configuracion.getPropiedad("Test_ReagendarVisita","PASS");
	private static final String encryptedData=Configuracion.getPropiedad("Test_ReagendarVisita","encryptedData");
	private static final String URL_BASE=Configuracion.getPropiedad("General","URL_BASE");
	
	WebDriver driver;
	Util util = new Util();
	Desagendar desAgendar = new Desagendar();
	DetalleVisita detalle = new DetalleVisita();
	
	@BeforeTest
	public void setUp() {
		System.getProperty("wedriver.chrome.driver", "/AutomationTestPrevencion/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(URL_BASE);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
	}
	
	@Test
	public void testDesagendarVisita() {
		
		Login login = new Login();
		LogOut logout = new LogOut();
		//Boolean testOk = true;
		
		//Obtiene el token para hacer desagendar la visita
		String token =PostLogin.loginBack(encryptedData);
		
		//Pide agendar la visita al back
		PostAgendar.agendarBack(token, "4003550", util.getDate(10)+" 10:00:00", util.getDate(10)+" 12:00:00");
		util.waitSecods(1);
		
		//Hace el login
		driver=login.login(driver, user, pass);
		util.waitSecods(3);
		
		try {
			
			//Espera la carga de la tabla
			util.esperarElemento(driver, "//*[@id=\\\"root\\\"]/div/div[1]/div/div[5]/div/div/table/tbody");
			
			//buscamos la visita que queremos agendar
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[5]/div/div/table/tbody/tr[10]/td[2]/div/div/a")).click();
			
			//desagendamos
			desAgendar.desagendar(driver);
			
			//obtiene el estado de la visita
			String estadoViistaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[4]/div/h6")).getText();
			
			//obtiene la fecha agendada
			String fechaAgendadaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[3]/div[1]/p")).getText();
			
			//obtiene el horario
			String horarioObtenido=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[3]/div[2]/p")).getText();
			
			//obtiene la duracion
			String duracionObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[3]/div[3]/p")).getText();
			//System.out.println(Assert.assertEquals("Sin agendar",estadoViistaObtenida));
			
			Assert.assertEquals("Sin agendar",estadoViistaObtenida);
			Assert.assertEquals("-",fechaAgendadaObtenida);
			Assert.assertEquals("-",horarioObtenido);
			Assert.assertEquals("-",duracionObtenida);
			
			Azure.RunTest(14734, 23515, 7716);
		
		}catch(Exception e) {
			util.screenShot(driver, "Test_DesagendarVisita");
			Assert.fail();
			System.out.println(e.getMessage());
		}
		
				
		//hacemos el logout
		logout.logout(driver);
	
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
}