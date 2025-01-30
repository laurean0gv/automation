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
import metodos.Desagendar;
import metodos.DetalleVisita;
import metodos.LogOutPreventores;
import metodos.LoginPreventores;
import utils.Configuracion;
import utils.Util;

public class Test_03_DesagendarVisita {
	
	//obtiene los datos para la prueba desde el archivo properties
	private static final String user=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_USER");
	private static final String pass=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_PASS");
	private static final String encryptedData=Configuracion.getPropiedad("Test_Preventores","TEST_01_PREVENTORES_encryptedData");
	private static final String URL_BASE=Configuracion.getPropiedad("General","URL_PREVENTORES_BASE");
	private static final String proyecto = Configuracion.getPropiedad("General", "PROYECTO_PREVENTORES");
	
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
			
		//Pide agendar la visita al back
		PostAgendar.agendarBack(encryptedData, "4003550", Util.getDate(10)+" 10:00:00", Util.getDate(10)+" 12:00:00");
		Util.waitSecods(1);
		
		//Hace el login
		driver=LoginPreventores.login(driver, user, pass);
		Util.waitSecods(3);
		
		try {
			
			//Espera la carga de la tabla
			Util.esperarElemento(driver, "(//a[contains(text(),'ALQUIVIAL S R L')])[2]");
			
			//buscamos la visita que queremos agendar
			driver.findElement(By.xpath("(//a[contains(text(),'ALQUIVIAL S R L')])[2]")).click();
			
			//desagendamos
			Desagendar.desagendar(driver);
			
			
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
			Azure.RunTest(proyecto, 14734, 23515, 23518);
		
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			//saca un print de pantalla
			Util.screenShot(driver, "Test_DesagendarVisita");
			//da por fallado el caso
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