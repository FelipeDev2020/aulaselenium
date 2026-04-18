import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {

    @Test
    public void testeTextField(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));

        // *** Pego o caminho dinamicamente na minha pasta raiz do projeto para abrir o formulario ***
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        // *** Consigo acessar o campo de texto pelo ID e adicionar um valor nele ***
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");

        // ***  Aqui consigo me certificar se o campo está preenchido ***
        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

    }

    @Test
    public void deveInteragirComTextArea() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Estou escrevendo aqui");
        Assert.assertEquals("Estou escrevendo aqui", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }
}
