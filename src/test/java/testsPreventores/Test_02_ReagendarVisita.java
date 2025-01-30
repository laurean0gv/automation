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
import backend.PostAgendar;
import metodos.AgendarReagendar;
import metodos.DetalleVisita;
import metodos.LogOutPreventores;
import metodos.LoginPreventores;
import utils.Configuracion;
import utils.Util;

public class Test_02_ReagendarVisita {

	private static final String user=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_USER");
	private static final String pass=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_PASS");
	private static final String encryptedData=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_encryptedData");
	private static final String URL_BASE=Configuracion.getPropiedad("General","URL_PREVENTORES_BASE");
	private static final String proyecto = Configuracion.getPropiedad("General", "PROYECTO_PREVENTORES");
	
	WebDriver driver;
	Util util = new Util();
	AgendarReagendar reagendar = new AgendarReagendar();
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
	public void testReagendarVisita() {
		
		String fecha = Util.getDate(10);

		//Pide agendar la visita al back
		PostAgendar.agendarBack(encryptedData, "4003550", "10/10/2025 10:00:00", "10/10/2025 12:00:00");
		Util.waitSecods(1);
		
		//Hace el login
		driver=LoginPreventores.login(driver, user, pass);
		Util.waitSecods(3);
		
		try {
			
			//Espera la carga de la tabla
			Util.esperarElemento(driver, "(//a[contains(text(),'ALQUIVIAL S R L')])[2]");
			
			//buscamos la visita que queremos agendar
			driver.findElement(By.xpath("(//a[contains(text(),'ALQUIVIAL S R L')])[2]")).click();			
			
			//agendamos
			AgendarReagendar.agendar_reagendar(driver,fecha.replaceAll("/",""),"0845","0230",1);
			
			//obtiene el estado de la visita
			String estadoViistaObtenida=driver.findElement(By.xpath("//h6[contains(text(),'Agendada no realizada')]")).getText();

			//obtiene la fecha agendada
			String fechaAgendadaObtenida=driver.findElement(By.xpath("//p[contains(text(),'Fecha agendada')]//parent::div//parent::div/p[text()='"+fecha+"']")).getText();
			
			//obtiene el horario
			String horarioObtenido=driver.findElement(By.xpath("//p[contains(text(),'Horario')]//parent::div//parent::div/p[text()='08:45 hs']")).getText();
			
			//obtiene la duracion
			String duracionObtenida=driver.findElement(By.xpath("//p[contains(text(),'Duración')]//parent::div//parent::div/p[text()='02:30 hs']")).getText();
			
			//comparamos los resultados
			Assert.assertEquals("Agendada no realizada",estadoViistaObtenida);
			Assert.assertEquals(fecha,fechaAgendadaObtenida);
			Assert.assertEquals("08:45 hs",horarioObtenido);
			Assert.assertEquals("02:30 hs",duracionObtenida);
			
			//Ejecuta el caso en Azure
			Azure.RunTest(proyecto, 14734, 23515, 23517);
			
		
		}catch(Exception e) {
			Util.screenShot(driver, "Test_ReagendarVisita");
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