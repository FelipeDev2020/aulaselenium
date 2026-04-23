import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Stream;

public class TesteCampoTreinamento {

    @Test
    public void testeTextField() {
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

    @Test
    public void deveInteragirComRadioButton() {
        // *** seleciona uma opção do botão radio **
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();
    }

    @Test
    public void deveInteragirComCheckBox() {
        // *** Seleciona uma das opções de check box ***
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
        driver.quit();
    }

    @Test
    public void deveInteragirComCombo() {
        // *** Seleciona uma das opções de um menu suspenso ***
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        //combo.selectByIndex(2);
        //combo.selectByValue("superior");
        combo.selectByVisibleText("Mestrado");

        Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());

        driver.quit();
    }

    @Test
    public void deveVerificarValoreCombo() {
        // *** verifica se uma das opções existe na lista de escolha ***
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);

        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = options.stream()
                .anyMatch(p -> p.getText().equals("Mestrado"));

        Assert.assertTrue(encontrou);

    }

    @Test
    public void deveVerificarValoreComboMultiplo() {
        ///  *** Seleciona multiplas opções ***
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");

        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());

        driver.quit();
    }

    @Test
    public void deveClicarNoBotão() {
        /// *** Test para clicar no botão do site
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();

        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
        driver.quit();
    }

    @Test
    @Ignore
    public void deveInteragirComLinks() {
        /// *** Test para clicar no link do site
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.linkText("Voltar")).click();
        //Assert.fail();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
        driver.quit();
    }

    @Test
    public void deveBuscarTextoNaPagina(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //System.out.println(driver.findElement(By.tagName("body")).getText());

        //Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));

        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());

        driver.quit();
    }
}