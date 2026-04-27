import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Desafio1 {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void capturarDadosDoFormulario() {

        // ===== Nome =====
        WebElement nome = driver.findElement(By.id("elementosForm:nome"));
        nome.sendKeys("Felipe");
        Assert.assertEquals("Felipe", nome.getAttribute("value"));

        // ===== Sobrenome =====
        WebElement sobrenome = driver.findElement(By.id("elementosForm:sobrenome"));
        sobrenome.sendKeys("Xavier");
        Assert.assertEquals("Xavier", sobrenome.getAttribute("value"));

        // ===== Sexo =====
        WebElement sexo = driver.findElement(By.id("elementosForm:sexo:0"));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        sexo.click();
        Assert.assertTrue(sexo.isSelected());

        // ===== Comida favorita =====
        WebElement comida = driver.findElement(By.id("elementosForm:comidaFavorita:2")); // Pizza
        comida.click();
        Assert.assertTrue(comida.isSelected());

        // ===== Escolaridade =====
        WebElement escolaridadeElement = driver.findElement(By.id("elementosForm:escolaridade"));
        Select escolaridade = new Select(escolaridadeElement);
        escolaridade.selectByVisibleText("Mestrado");

        Assert.assertEquals(
                "Mestrado",
                escolaridade.getFirstSelectedOption().getText()
        );

        // ===== Esportes =====
        WebElement esportesElement = driver.findElement(By.id("elementosForm:esportes"));
        Select esportes = new Select(esportesElement);
        esportes.selectByVisibleText("Futebol");

        boolean encontrou = esportes.getAllSelectedOptions()
                .stream()
                .anyMatch(e -> e.getText().equals("Futebol"));

        Assert.assertTrue(encontrou);

        // ===== Submit =====
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        // ===== Resultado =====
        String resultado = driver.findElement(By.id("resultado")).getText();

        Assert.assertTrue(resultado.contains("Felipe"));
        Assert.assertTrue(resultado.contains("Xavier"));
        Assert.assertTrue(resultado.contains("Masculino"));
        Assert.assertTrue(resultado.contains("Pizza"));
        Assert.assertTrue(resultado.contains("mestrado"));
        Assert.assertTrue(resultado.contains("Futebol"));
    }
}