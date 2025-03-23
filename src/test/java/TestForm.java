import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class TestForm {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testLoginForm() {
        // TODO Убрать хардкод
        driver.get("file:/home/valery/IdeaProjects/TestForm/src/test/resources/qa-test.html");
        driver.manage().window().maximize();

        WebElement emailField = driver.findElement(By.id("loginEmail"));
        WebElement passwordField = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        emailField.sendKeys("test@protei.ru");
        passwordField.sendKeys("test");
        loginButton.click();

        // Проверка успешной авторизации
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputsPage")));
        Assert.assertTrue(driver.findElement(By.id("inputsPage")).isDisplayed(), "Авторизация не удалась");
    }

    @Test
    public void testDataForm() {
            WebElement dataEmail = driver.findElement(By.id("dataEmail"));
            WebElement dataName = driver.findElement(By.id("dataName"));
            WebElement dataGender = driver.findElement(By.id("dataGender"));
            WebElement dataCheck11 = driver.findElement(By.id("dataCheck11"));
            WebElement dataCheck12 = driver.findElement(By.id("dataCheck12"));
            WebElement dataSelect21 = driver.findElement(By.id("dataSelect21"));
            WebElement dataSelect22 = driver.findElement(By.id("dataSelect22"));
            WebElement dataSelect23 = driver.findElement(By.id("dataSelect23"));
            WebElement dataSendButton = driver.findElement(By.id("dataSend"));

            dataEmail.sendKeys("user@example.com");
            dataName.sendKeys("User");
            // TODO реализовать выбор гендера из выпадающего списка, а не подстановкой значения
            dataGender.click();
            dataGender.sendKeys("Женский");
            dataGender.click();
            dataGender.sendKeys("Мужской");
            dataCheck11.click();
            dataCheck12.click();
            dataSelect21.click();
            dataSelect22.click();
            dataSelect23.click();
            dataSendButton.click();

            // TODO Добавить xpath в enum файл
            WebElement popup = driver.findElement(By.xpath("//div[@class='uk-margin uk-modal-content' and contains(text(), 'Данные добавлены.')]"));
            WebElement popupOkButton = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
            popup.getText().equals("Данные добавлены.");
            popupOkButton.click();
    }

    @Test
    public void checkTable() {

        WebElement tableRow = driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr"));
        Assert.assertTrue(tableRow.getText().contains("user@example.com"));
        Assert.assertTrue(tableRow.getText().contains("User"));
        Assert.assertTrue(tableRow.getText().contains("Мужской"));
        Assert.assertTrue(tableRow.getText().contains("1.1"));
        Assert.assertTrue(tableRow.getText().contains("1.2"));
        Assert.assertTrue(tableRow.getText().contains("2.3"));
    }

    @AfterClass
    public void quitBrowser() {
        driver.quit();
    }
}
