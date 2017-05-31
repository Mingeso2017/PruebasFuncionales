/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupot.mingesofunctionaltest;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;
/**
 *
 * @author RaulMaster
 */
public class Logeo {
    
    //Web driver de selenium
    private static WebDriver driver = null;
    //Elementos para enviar el resultado al testlink
    //Api del testlink
    public static String DEV_KEY = "9cc3a810b19e2d7436a0b7b60f9bd9a2"; //Your API
    public static String SERVER_URL = "http://localhost/testlink/lib/api/xmlrpc/v1/xmlrpc.php";//your testlink server url
    public static String PROJECT_NAME = "Final Mingeso Test"; 
    public static String PLAN_NAME = "Plan de Prueba CoS";
    public static String BUILD_NAME = "V 1.0";
    public static String Case_Name = "Logeo [1]"; 
 
    //nombre elemento para realizar la prueba
    private static String nombreCliente = "raul";
    private static String passCliente = "olivares";
    

    
    public Logeo(){
    }
    
    @BeforeClass
    public static void InicializarDriver(){
        // magia informatica por q la ultima version de selenium no inicia automaticamente el firefox
        System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe");
        //iniciamos el driver
        driver = new FirefoxDriver();
    }
    
    @AfterClass
    public static void CerrarDriver(){
        
        driver.quit();
    }
    
    @Test
    public void Logearse() throws TestLinkAPIException{
	String notes="Execution failed: ";
	String result=TestLinkAPIResults.TEST_FAILED;
  
        try{
            //nos dirigimos al login
            driver.get("http://localhost:8080/TingesoPep2/");  
            
            //buscamos la casilla para ingresar el nombre de usuario
            WebElement ingresarUser = driver.findElement(By.id("j_idt5:j_idt9"));
            ingresarUser.sendKeys(nombreCliente);
            //buscamos la casilla para ingresar el pass
            WebElement ingresarPass = driver.findElement(By.id("j_idt5:j_idt11"));
            ingresarPass.sendKeys(passCliente);
            //buscamos el boton aceptar
            WebElement botonEntrar = driver.findElement(By.id("j_idt5:j_idt13"));
            botonEntrar.click();
            
            //actualizar el driver a la nueva pagina q nos manden
            driver.switchTo().defaultContent();
            
            //esperar segundos para 
            WebDriverWait espera = new WebDriverWait(driver, 100);
            WebElement LabelCliente = driver.findElement(By.id("j_idt6:j_idt9"));
            espera.until(ExpectedConditions.visibilityOf(LabelCliente));
          
            //Enviamos respuesta al testlink
            result= TestLinkAPIResults.TEST_PASSED;
            notes="Executed successfully";
        }
        
        finally{
            reportResult(PROJECT_NAME, PLAN_NAME, Case_Name , BUILD_NAME, notes, result);
        }
    }
    
    //funcion para enviar reslutados al testlink
    public static void reportResult(String TestProject,String TestPlan,String Testcase,String Build,String Notes,String Result) throws TestLinkAPIException{
	TestLinkAPIClient api=new TestLinkAPIClient(DEV_KEY, SERVER_URL);
	api.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, Result);
    }
}
/*
<input id="j_idt5:j_idt9" Ingresar User

<input id="j_idt5:j_idt11" name="j_idt5:j_idt11"  Ingresar Pass

<button id="j_idt5:j_idt13" name="j_idt5:j_idt13" boton entrar

<label id="j_idt6:j_idt9" label con el nombre completo del cliente
*/