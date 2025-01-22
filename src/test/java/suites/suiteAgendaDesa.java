package suites;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import backend.Azure;
import tests.Test_AgendarVisita;
import tests.Test_DesagendarVisita;
import tests.Test_ReagendarVisita;


public class suiteAgendaDesa {
	
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
	public void agendarVisita() {
		
		Test_AgendarVisita agendar = new Test_AgendarVisita();
		agendar.testCase(driver);
		
	}
		
	
	@Test
	public void reagendarVisita() {

		Test_ReagendarVisita reagendar = new Test_ReagendarVisita();
		reagendar.testCase(driver);		
		
	}
	
	@Test
	public void desAgendarVisita() {

		Test_DesagendarVisita desagendar = new Test_DesagendarVisita();
		desagendar.testCase(driver);		
		
	}
		
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
	
}
