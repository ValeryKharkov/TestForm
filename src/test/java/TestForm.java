import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestForm {
    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        // TODO Убрать хардкод
        driver.get("file:/home/valery/IdeaProjects/TestForm/src/test/resources/qa-test.html");

        WebElement emailField = driver.findElement(By.name("loginEmail"));
        WebElement passwordField = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        emailField.sendKeys("test@protei.ru");
        passwordField.sendKeys("test");
        loginButton.click();

        driver.quit();
    }
}
