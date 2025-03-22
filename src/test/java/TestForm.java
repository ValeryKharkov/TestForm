import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestForm {

    @Test
    public void testLogin() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        // TODO Убрать хардкод
        driver.get("file:/home/valery/IdeaProjects/TestForm/src/test/resources/qa-test.html");
        driver.manage().window().maximize();

//        WebElement emailField = driver.findElement(By.xpath("//input[@id='loginEmail']"));
        WebElement emailField = driver.findElement(By.id("loginEmail"));
        WebElement passwordField = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        emailField.sendKeys("test@protei.ru");
        passwordField.sendKeys("test");
        loginButton.click();

        driver.quit();
    }
}
