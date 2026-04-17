import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class PrimeiroTeste {
    public static void main(String[] args) {
        // 1. Inicia o navegador (ChromeDriver é gerenciado automaticamente)
        WebDriver driver = new ChromeDriver();

        try {
            // 2. Abre uma página
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");

            // 3. Espera implícita (boa prática)
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            // 4. Encontra elementos
            WebElement caixaTexto = driver.findElement(By.name("my-text"));
            WebElement botaoSubmit = driver.findElement(By.cssSelector("button"));

            // 5. Interage
            caixaTexto.sendKeys("Olá Selenium!");
            botaoSubmit.click();

            // 6. Verifica o resultado
            WebElement mensagem = driver.findElement(By.id("message"));
            System.out.println("Mensagem recebida: " + mensagem.getText());

            System.out.println("✅ Teste executado com sucesso!");

        } finally {
            // 7. Fecha o navegador
            driver.quit();
        }
    }
}