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


public class logeoSelenium{
    
    //Web driver de selenium
    private static WebDriver driver = null;

    //nombre elemento para realizar la prueba
    private static String nombreCliente = "raul";
    private static String passCliente = "olivares";
    

    
    public logeoSelenium(){
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
    public void Logearse(){
  
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
    
    } 

}
