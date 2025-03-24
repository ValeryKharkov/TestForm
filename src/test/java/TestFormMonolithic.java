import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestFormMonolithic {

    @Test
    public void testLogin() {
        String CHROME_DRIVER_PATH = "/usr/local/bin/chromedriver";
        String TEST_PAGE_URL = "file:/home/valery/IdeaProjects/TestForm/src/test/resources/qa-test.html";
        String XPATH_DATA_TABLE = "//table[@id='dataTable']/tbody/tr";
        String XPATH_POPUP = "//div[@class='uk-margin uk-modal-content' and contains(text(), 'Данные добавлены.')]";
        String XPATH_POPUP_OK_BUTTON = "//button[contains(text(), 'Ok')]";

        // Подготовка
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(TEST_PAGE_URL);
        driver.manage().window().maximize();

        // Страница авторизации
        WebElement emailField = driver.findElement(By.id("loginEmail"));
        WebElement passwordField = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        emailField.sendKeys("test@protei.ru");
        passwordField.sendKeys("test");
        loginButton.click();

        WebElement inputsPage = driver.findElement(By.id("inputsPage"));
        assertTrue(inputsPage.isDisplayed());

        // Страница анкеты. Ввод и добавление данных
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
        // TODO реализовать выбор гендера из выпадающего списка
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

        WebElement popup = driver.findElement(By.xpath(XPATH_POPUP));
        WebElement popupOkButton = driver.findElement(By.xpath(XPATH_POPUP_OK_BUTTON));
        popup.getText().equals("Данные добавлены.");
        popupOkButton.click();

        // Страница анкеты. Проверка таблицы с добавленными данными
        WebElement tableRow = driver.findElement(By.xpath(XPATH_DATA_TABLE));
        assertTrue(tableRow.getText().contains("user@example.com"));
        assertTrue(tableRow.getText().contains("User"));
        assertTrue(tableRow.getText().contains("Мужской"));
        assertTrue(tableRow.getText().contains("1.1"));
        assertTrue(tableRow.getText().contains("1.2"));
        assertTrue(tableRow.getText().contains("2.3"));

        driver.quit();
    }
}
