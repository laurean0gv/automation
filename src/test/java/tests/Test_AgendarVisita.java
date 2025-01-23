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
import backend.DeleteDesagendar;
import backend.PostLogin;
import funciones.LogOut;
import funciones.Login;
import funciones.AgendarReagendar;
import funciones.DetalleVisita;
import utils.Configuracion;
import utils.Util;

public class Test_AgendarVisita {

	private static final String user=Configuracion.getPropiedad("Test_AgendarVisita","USER");
	private static final String pass=Configuracion.getPropiedad("Test_AgendarVisita","PASS");
	private static final String encryptedData=Configuracion.getPropiedad("Test_AgendarVisita","encryptedData");
	private static final String URL_BASE=Configuracion.getPropiedad("General","URL_BASE");
	
	WebDriver driver;
	Util util = new Util();
	AgendarReagendar agendar = new AgendarReagendar();
	DetalleVisita detalle = new DetalleVisita();
	String fecha = util.getDate(10);
	
	@BeforeTest
	public void setUp() {
		System.getProperty("wedriver.chrome.driver", "/AutomationTestPrevencion/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(URL_BASE);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
	}
	
	@Test
	public void testAgendarVisita() {
		
		Login login = new Login();
		LogOut logout = new LogOut();
		
		
		//Obtiene el token para hacer desagendar la visita
		String token =PostLogin.loginBack(encryptedData);
		
		//Pide desagendar la visita al back
		DeleteDesagendar.desagendarBack(token,"4003550");
		util.waitSecods(1);
		
		//Hace el login
		driver=login.login(driver, user, pass);
		util.waitSecods(4);
		
		try {
			
			//Espera la carga de la tabla
			util.esperarElemento(driver, "//*[@id=\"root\"]/div/div[1]/div/div[5]/div/div/table/tbody");
			
			//buscamos la visita que queremos agendar
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[5]/div/div/table/tbody/tr[10]/td[2]/div/div/a")).click();
			
			//agendamos
			agendar.agendar_reagendar(driver,fecha.replaceAll("/",""),"1000","");
			
			//obtiene el estado de la visita
			String estadoViistaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[4]/div/h6")).getText();
			
			//obtiene la fecha agendada
			String fechaAgendadaObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[3]/div[1]/p")).getText();
			
			//obtiene el horario
			String horarioObtenido=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[3]/div[2]/p")).getText();
			
			//obtiene la duracion
			String duracionObtenida=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[3]/div[3]/p")).getText();
			
			Assert.assertEquals("Agendada no realizada",estadoViistaObtenida, "caso feliz");
			Assert.assertEquals(fecha,fechaAgendadaObtenida);
			Assert.assertEquals("10:00 hs",horarioObtenido);
			Assert.assertEquals("01:00 hs",duracionObtenida);
			
			Azure.RunTest(14734, 23515, 7714);
						
							
		
		}catch(Exception e) {
			util.screenShot(driver, "Test_AgendarVisita");
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