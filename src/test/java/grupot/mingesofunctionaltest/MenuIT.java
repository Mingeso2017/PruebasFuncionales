/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupot.mingesofunctionaltest;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author RaulMaster
 */
public class MenuIT {
    
    private static WebDriver driver = null;
    //nombre elemento para realizar la prueba
    private static String nombrePlato = "mani confitado";
    
    public MenuIT() {
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
    public void CrearNuevoMenu(){
        driver.get("http://localhost:8080/Mingeso/faces/app/menu/index.xhtml");
        
        //find Element busca el boton crear a partir de su nobmre
        WebElement botonCrear = driver.findElement(By.name("MenuListForm:createButton")) ;
        //hacemos click en el boton
        botonCrear.click();
        
        //esperamos maximo 10 segundos
        WebDriverWait espera = new WebDriverWait(driver, 5);
        //buscamos el dialogo creado para ingresar nuevo menu
        WebElement TablaCrearMenu = driver.findElement(By.id("MenuCreateDlg_title"));
        //paramos de esperar si es q la tabla ya es visible
        espera.until(ExpectedConditions.visibilityOf(TablaCrearMenu));
        
        //escribimos elementos
        WebElement ingresarNombre = driver.findElement(By.id("MenuCreateForm:nombreMenu"));
        ingresarNombre.sendKeys(nombrePlato);
        WebElement ingresarPrecio = driver.findElement(By.id("MenuCreateForm:precioMenu"));
        ingresarPrecio.sendKeys("3500");
        WebElement ingresarCantidad = driver.findElement(By.id("MenuCreateForm:cantDisponible"));
        ingresarCantidad.sendKeys("100");
        WebElement ingresarTiempo = driver.findElement(By.id("MenuCreateForm:tiempoPreparacion"));
        ingresarTiempo.sendKeys("2");
                
        //accionamos el boton aceptar        
        WebElement aceptarBoton = driver.findElement(By.id("MenuCreateForm:j_idt93"));
        aceptarBoton.click();
        //accionamos boton OK
        WebElement confirmarBoton = driver.findElement(By.id("MenuListForm:j_idt51"));
        confirmarBoton.click();        
       
        
        
    }
    
    @Test
    public void ComprobarPrecioReal() {
        
                
    }
    
    /*
    //INGRESO PRECIO AL MENU EN EL CREATE-MENU
    <input id="MenuCreateForm:precioMenu" name="MenuCreateForm:precioMenu" 
    value="0" title="Precio Menu" 
    class="ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all" role="textbox" 
    aria-disabled="false" aria-readonly="false" type="text">
    
    //BASE DATOS MENU ID = 1
    <tr data-ri="0" data-rk="1" class="ui-widget-content ui-datatable-even ui-datatable-selectable" role="row" aria-selected="false">
    <td role="gridcell">1</td>
    <td role="gridcell">mouse chcolate</td>
    <td role="gridcell">2000</td>
    <td role="gridcell">26</td>
    <td role="gridcell">10</td>
    <td role="gridcell">0</td>
    
    //BOTON CREATE 
    <button id="MenuListForm:createButton" name="MenuListForm:createButton" 
    class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left"
    onclick="PrimeFaces.ab({s:'MenuListForm:createButton',u:'MenuCreateForm',onco:function(xhr,status,args){PF('MenuCreateDialog').show();}});return false;" 
    type="submit" role="button" aria-disabled="false">
    <span class="ui-button-icon-left ui-icon ui-c ui-icon-plus"></span>
    <span class="ui-button-text ui-c">Crear</span></button>
    barara despues de hacer click
    <<span id="MenuCreateDlg_title" class="ui-dialog-title">Create New Menu</span>
    */
    
}
