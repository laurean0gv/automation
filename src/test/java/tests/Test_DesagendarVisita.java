package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
	
	WebDriver driver;
	Util util = new Util();
	Desagendar desAgendar = new Desagendar();
	DetalleVisita detalle = new DetalleVisita();
	
	
	@Test
	public void testCase(WebDriver driver) {
		
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
			util.esperarElemento(driver, "//*[@id=\"root\"]/div/div[1]/div/div[4]");
			
			//buscamos la visita que queremos agendar
			driver=detalle.accederDetalleVisita(driver, "//*[@id=\"root\"]/div/div[1]/div/div[4]/div/div/table/tbody/tr[10]/td[2]/div/div/a", "ALQUIVIAL S R L");
			
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
}