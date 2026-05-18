import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Desafio2 {

    private WebDriver driver;

    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void sair(){
        driver.quit();
    }


    @Test
    public void validacaoDoCampoNome(){
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta = driver.switchTo().alert();
        String msg = alerta.getText();
        Assert.assertEquals("Nome eh obrigatorio", msg);
        alerta.accept();
    }

    @Test
    public void validacaoDoCampoSobrenome() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Felipe");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta = driver.switchTo().alert();
        String msg = alerta.getText();
        Assert.assertEquals("Sobrenome eh obrigatorio", msg);
        alerta.accept();
    }

    @Test
    public void validacaoDoCampoSexo() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Felipe");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Xavier");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta = driver.switchTo().alert();
        String msg = alerta.getText();
        Assert.assertEquals("Sexo eh obrigatorio", msg);
        alerta.accept();
    }

    @Test
    public void validacaoDoCampoComidaFavorita() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Felipe");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Xavier");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta = driver.switchTo().alert();
        String msg = alerta.getText();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);
        alerta.accept();
    }

    @Test
    public void validacaoDoCampoEsporte() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Felipe");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Xavier");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select listaEsporte = new Select(element);
        listaEsporte.selectByVisibleText("Futebol");
        listaEsporte.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta = driver.switchTo().alert();
        String msg = alerta.getText();
        Assert.assertEquals("Voce faz esporte ou nao?", msg);
        alerta.accept();
    }
}
