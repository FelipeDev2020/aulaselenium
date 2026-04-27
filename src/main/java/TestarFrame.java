import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestarFrame {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void sair(){driver.quit();}

    @Test
    public void deveInteragirComFrame(){
        // *** O switchTo é usado para trazer o foco para o frame ***
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alerta = driver.switchTo().alert();
        String msg = alerta.getText();
        Assert.assertEquals("Frame OK!", msg);
        alerta.accept();
        // *** O switchTo é usado para trazer o foco da pagina, no caso para focar na pagina inicial ***
        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
    }

    @Test
    public void deveInteragirComJanelas(){

        // guarda a janela principal
        String janelaPrincipal = driver.getWindowHandle();

        driver.findElement(By.id("buttonPopUpEasy")).click();

        // espera abrir nova janela
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getWindowHandles().size() > 1);

        // troca para o popup
        for (String janela : driver.getWindowHandles()) {
            if (!janela.equals(janelaPrincipal)) {
                driver.switchTo().window(janela);
            }
        }

        // interage com popup
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");

        // fecha popup
        driver.close();

        // ️ volta corretamente para a principal
        driver.switchTo().window(janelaPrincipal);

        // continua teste
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
    }
}
