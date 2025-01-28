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
	
	//obtiene los datos para la prueba desde el archivo properties
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
			util.esperarElemento(driver, "(//a[contains(text(),'ALQUIVIAL S R L')])[2]");
			
			//buscamos la visita que queremos agendar
			driver.findElement(By.xpath("(//a[contains(text(),'ALQUIVIAL S R L')])[2]")).click();
			
			
			//desagendamos
			desAgendar.desagendar(driver);
			
			
			//Compara los resultados
			//el estado de la visita
			Assert.assertEquals("Sin agendar",driver.findElement(By.xpath("//h6[contains(text(),'Sin agendar')]")).getText());
			
			//la fecha agendada
			Assert.assertEquals("-",driver.findElement(By.xpath("//p[contains(text(),'Fecha agendada')]//parent::div//parent::div/p[text()='-']")).getText());
			
			//el horario
			Assert.assertEquals("-",driver.findElement(By.xpath("//p[contains(text(),'Horario')]//parent::div//parent::div/p[text()='-']")).getText());
			
			//la duracion
			Assert.assertEquals("-",driver.findElement(By.xpath("//p[contains(text(),'Duración')]//parent::div//parent::div/p[text()='-']")).getText());			
			
			//ejecuta el caso en azure
			Azure.RunTest(14734, 23515, 23518);
		
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			
			//saca un print de pantalla
			util.screenShot(driver, "Test_DesagendarVisita");
			
			//da por fallado el caso
			Assert.fail();
		}
		
				
		//hacemos el logout
		logout.logout(driver);
	
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
}