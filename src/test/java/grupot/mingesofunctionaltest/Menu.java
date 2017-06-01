package grupot.mingesofunctionaltest;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static org.openqa.selenium.Keys.TAB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;
/**
 *
 * @author RaulMaster
 */
public class Menu {
    
    //Web driver de selenium
    private static WebDriver driver = null;
    //Elementos para enviar el resultado al testlink
    //Api del testlink
    public static String DEV_KEY = "9cc3a810b19e2d7436a0b7b60f9bd9a2"; //Your API
    public static String SERVER_URL = "http://localhost/testlink/lib/api/xmlrpc/v1/xmlrpc.php";//your testlink server url
    public static String PROJECT_NAME = "Final Mingeso Test"; 
    public static String PLAN_NAME = "Plan de Prueba CoS";
    public static String BUILD_NAME = "V 1.0";
    public static String Case_Name = "Menu [1]"; 
 
    //nombre elemento para realizar la prueba
    private static String nombreCliente = "raul";
    private static String passCliente = "olivares";
    
    private static String fecha = "02-05-17";

    private static String monto = "8000";


    
    public Menu(){
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
    public void IngresarOrden() throws TestLinkAPIException, InterruptedException{
        String notes="Execution failed: no se a podido ingresar un menu correctamente ";
	String result=TestLinkAPIResults.TEST_FAILED;
        
        try{
            //Primero nos logeamos correctamente
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
            WebDriverWait espera = new WebDriverWait(driver, 200);
            WebElement LabelCliente = driver.findElement(By.id("j_idt6:j_idt9"));
            espera.until(ExpectedConditions.visibilityOf(LabelCliente));
          
            //Ahora ingresamos los valores al menu
            //ingresamos una fecha en el calendario
                WebElement calendario = driver.findElement(By.id("j_idt6:datetime_input"));
                calendario.click();
                //navegamos por el calendario hasta la fecha 28
                List<WebElement> allDates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
                for(WebElement ele:allDates)
		{
			
			String date=ele.getText();
			
			if(date.equalsIgnoreCase("2"))
			{
				ele.click();
				break;
			}
			
		}
            
            //press tab
            calendario.click();
            calendario.sendKeys(Keys.TAB);
            
            //comida
            WebElement selectPlato = driver.findElement(By.id("j_idt6:j_idt12_label"));
            selectPlato.sendKeys(Keys.TAB);
            
            //edificio
            WebElement selectUbicacion = driver.findElement(By.id("j_idt6:j_idt15_label"));
            selectUbicacion.sendKeys(Keys.TAB);               
            
            
                WebElement ingresarMonto = driver.findElement(By.id("j_idt6:j_idt18"));
                ingresarMonto.clear();
                ingresarMonto.sendKeys(monto);
            
            //click en ordenar comida
            WebElement botonOrdenar = driver.findElement(By.id("j_idt6:j_idt24"));
            botonOrdenar.click();

            driver.switchTo().defaultContent();
            //esperar segundos para 
            WebDriverWait espera2 = new WebDriverWait(driver, 2000);
            WebElement mensaje = driver.findElement(By.xpath("//span[@class='ui-messages-info-detail']"));
            espera2.until(ExpectedConditions.visibilityOf(mensaje));
            //<span class="ui-messages-info-detail">
            
            
            //Enviamos respuesta al testlink
            result= TestLinkAPIResults.TEST_PASSED;
            notes="Executed successfully: se agrego nueva orden";
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
//fecha
<input id="j_idt6:j_idt11_input" 
name="j_idt6:j_idt11_input" 

//comida mentira :C
<label id="j_idt6:j_idt13_label" 
class="ui-selectonemenu-label ui-inputfield ui-corner-all">empanadas 1200</label>

<select id="j_idt6:j_idt13_input" name="j_idt6:j_idt13_input" tabindex="-1" aria-hidden="true">
<option value="1">mouse chcolate 2000</option>
<option value="2">lentejas 1800</option>
<option value="3">pollo con arroz 2500</option>
<option value="4">empanadas 1200</option>
<option value="6">mani confitado 3500</option>
<option value="7">cebolla en escabeche 200</option>
<option value="16">Mani Salado 3500</option></select>

<ul id="j_idt6:j_idt13_items"
class="ui-selectonemenu-items ui-selectonemenu-list ui-widget-content ui-widget ui-corner-all ui-helper-reset" role="listbox" aria-activedescendant="j_idt6:j_idt13_3"><li class="ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all" data-label="mouse chcolate 2000" tabindex="-1" role="option" id="j_idt6:j_idt13_0">mouse chcolate 2000</li><li class="ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all" data-label="lentejas 1800" tabindex="-1" role="option" id="j_idt6:j_idt13_1">lentejas 1800</li><li class="ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all" data-label="pollo con arroz 2500" tabindex="-1" role="option" id="j_idt6:j_idt13_2">pollo con arroz 2500</li><li class="ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all ui-state-highlight" data-label="empanadas 1200" tabindex="-1" role="option" id="j_idt6:j_idt13_3">empanadas 1200</li><li class="ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all" data-label="mani confitado 3500" tabindex="-1" role="option" id="j_idt6:j_idt13_4">mani confitado 3500</li><li class="ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all" data-label="cebolla en escabeche 200" tabindex="-1" role="option" id="j_idt6:j_idt13_5">cebolla en escabeche 200</li><li class="ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all" data-label="Mani Salado 3500" tabindex="-1" role="option" 
id="j_idt6:j_idt13_6">Mani Salado 3500</li></ul>


Dinero monto
<input id="j_idt6:j_idt19" name="j_idt6:j_idt19"
value="0" aria-required="true" 
class="ui-inputfield ui-inputtext ui-widget ui-state-default 
ui-corner-all" role="textbox" aria-disabled="false" 
aria-readonly="false" type="text">

//boton
<button id="j_idt6:j_idt25" name="j_idt6:j_idt25"

//mensaje
<span class="ui-messages-info-detail">Se ha ordenado su comida</span>

<div class="ui-messages-info ui-corner-all"><span class="ui-messages-info-icon"></span><ul><li>
<span class="ui-messages-info-detail">Se ha ordenado su comida</span></li></ul></div>
*/