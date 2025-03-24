import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestFormModule {
    private static WebDriver driver;
    public static final String CHROME_DRIVER_PATH = "/usr/local/bin/chromedriver";
    public static final String TEST_PAGE_URL = "file:/home/valery/IdeaProjects/TestForm/src/test/resources/qa-test.html";
    public static final String XPATH_DATA_TABLE = "//table[@id='dataTable']/tbody/tr";
    public static final String XPATH_POPUP = "//div[@class='uk-margin uk-modal-content' and contains(text(), 'Данные добавлены.')]";
    public static final String XPATH_POPUP_OK_BUTTON = "//button[contains(text(), 'Ok')]";


    @BeforeEach
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TEST_PAGE_URL);
    }

    @Test
    public void loginFormTest() {
        WebElement emailField = driver.findElement(By.id("loginEmail"));
        WebElement passwordField = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        emailField.sendKeys("test@protei.ru");
        passwordField.sendKeys("test");
        loginButton.click();

        assertTrue(driver.findElement(By.id("inputsPage")).isDisplayed(), "Авторизация не удалась");
    }

    @Test()
    public void inputFormTest() {
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

//        Select genderSelect = new Select(dataGender);
//        genderSelect.selectByVisibleText("Мужской");

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_POPUP)));
        WebElement popupOkButton = driver.findElement(By.xpath(XPATH_POPUP_OK_BUTTON));
        assertEquals("Данные добавлены.", popup.getText());
        popupOkButton.click();
    }

    @Test()
    public void checkTableTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tableRow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_DATA_TABLE)));
//        WebElement tableRow = driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr"));
        assertTrue(tableRow.getText().contains("user@example.com"));
        assertTrue(tableRow.getText().contains("User"));
        assertTrue(tableRow.getText().contains("Мужской"));
        assertTrue(tableRow.getText().contains("1.1"));
        assertTrue(tableRow.getText().contains("1.2"));
        assertTrue(tableRow.getText().contains("2.3"));
    }

    @AfterEach
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }

    }
}
