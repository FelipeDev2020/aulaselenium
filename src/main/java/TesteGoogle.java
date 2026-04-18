import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

    @Test
    public void teste() {
        // *** Definindo com qual navegador trabalhar  ***
        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();

        //*** Código para definir o tamanho da tela do navegador ***
        //driver.manage().window().setSize(new Dimension(1200, 765));
        driver.manage().window().maximize();

        // *** Definindo o site que será aberto no navegador **
        driver.get("http://www.google.com");

        // *** Código de comparação de valores de atributos esperados (Test) ***
        Assert.assertEquals("Google", driver.getTitle());

        // *** Para fechar o navegador  ***
        driver.quit();
    }
}
