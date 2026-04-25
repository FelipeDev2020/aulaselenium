import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestAlert {
    @Test
    public void deveInteragirAlertSimples() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("alert")).click();
        // *** Muda o foco da pagina principal para o Pop-Up do alerta **
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Alert Simples", texto);
        // *** Clica no ok do alert **
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
        driver.quit();

    }

    @Test
    public void deveInteragirAlertConfirm() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Confirm Simples", texto);
        alert.accept();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);

        driver.findElement(By.id("confirm")).click();
        Alert alert2 = driver.switchTo().alert();
        String texto2 = alert2.getText();
        Assert.assertEquals("Confirm Simples", texto2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alert.dismiss();//Negado
        texto2 = alert2.getText();
        Assert.assertEquals("Negado", texto2);
        alert.accept();

        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys(texto2);

        driver.quit();
    }

    @Test
    public void deveInteragirAlertPrompt() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1900, 750));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("prompt")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("27");
        alerta.accept();
        Assert.assertEquals("Era 27?", alerta.getText());
        alerta.accept();
        Assert.assertEquals(":D", alerta.getText());
        alerta.accept();

        driver.quit();

    }
}
