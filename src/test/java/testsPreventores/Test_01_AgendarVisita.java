	package testsPreventores;

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
import metodos.AgendarReagendar;
import metodos.LogOutPreventores;
import metodos.LoginPreventores;
import utils.Configuracion;
import utils.Util;

public class Test_01_AgendarVisita {

	private static final String user=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_USER");
	private static final String pass=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_PASS");
	private static final String encryptedData=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_encryptedData");
	private static final String URL_BASE=Configuracion.getPropiedad("General","URL_PREVENTORES_BASE");
	private static final String proyecto = Configuracion.getPropiedad("General", "PROYECTO_PREVENTORES");
	
	WebDriver driver;
	String fecha = Util.getDate(10);
		
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
				
		//Pide desagendar la visita al back
		DeleteDesagendar.desagendarBack(encryptedData,"4003550");
		Util.waitSecods(1);
		
		//Hace el login
		driver=LoginPreventores.login(driver, user, pass);
		Util.waitSecods(4);
		
		try {
			
			//Espera la carga de la tabla
			Util.esperarElemento(driver, "(//a[contains(text(),'ALQUIVIAL S R L')])[2]");
			
			//buscamos la visita que queremos agendar
			driver.findElement(By.xpath("(//a[contains(text(),'ALQUIVIAL S R L')])[2]")).click();
			
			//agendamos
			AgendarReagendar.agendar_reagendar(driver,fecha.replaceAll("/",""),"1000","",2);
			
					
			//obtiene el estado de la visita
			String estadoViistaObtenida=driver.findElement(By.xpath("//h6[contains(text(),'Agendada no realizada')]")).getText();
			
			//obtiene la fecha agendada
			String fechaAgendadaObtenida=driver.findElement(By.xpath("//p[contains(text(),'Fecha agendada')]//parent::div//parent::div/p[text()='"+fecha+"']")).getText();
				
			//obtiene el horario
			String horarioObtenido=driver.findElement(By.xpath("//p[contains(text(),'Horario')]//parent::div//parent::div/p[text()='10:00 hs']")).getText();
			
			//obtiene la duracion
			String duracionObtenida=driver.findElement(By.xpath("//p[contains(text(),'Duración')]//parent::div//parent::div/p[text()='01:00 hs']")).getText();
			
			Assert.assertEquals("Agendada no realizada",estadoViistaObtenida, "caso feliz");
			Assert.assertEquals(fecha,fechaAgendadaObtenida);
			Assert.assertEquals("10:00 hs",horarioObtenido);
			Assert.assertEquals("01:00 hs",duracionObtenida);
			
			//ejecuta el caso en azure
			Azure.RunTest(proyecto, 14734, 23515, 23516);
						
							 
		
		}catch(Exception e) {
			Util.screenShot(driver, "Test_AgendarVisita");
			System.out.println(e.getMessage());
			Assert.fail();
		}
		
				
		//hacemos el logout
		LogOutPreventores.logout(driver);
	
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
}