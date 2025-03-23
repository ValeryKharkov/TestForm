import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class TestForm {

    @Test
    public void testLogin() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        // TODO Убрать хардкод
        driver.get("file:/home/valery/IdeaProjects/TestForm/src/test/resources/qa-test.html");
        driver.manage().window().maximize();

        // Страница авторизации
//        WebElement emailField = driver.findElement(By.xpath("//input[@id='loginEmail']"));
        WebElement emailField = driver.findElement(By.id("loginEmail"));
        WebElement passwordField = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        emailField.sendKeys("test@protei.ru");
        passwordField.sendKeys("test");
        loginButton.click();

        WebElement inputsPage = driver.findElement(By.id("inputsPage"));
        Assert.assertTrue(inputsPage.isDisplayed());

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

        // TODO Добавить xpath в enum файл
        WebElement popup = driver.findElement(By.xpath("//div[@class='uk-margin uk-modal-content' and contains(text(), 'Данные добавлены.')]"));
        WebElement popupOkButton = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
        popup.getText().equals("Данные добавлены.");
        popupOkButton.click();

        // Страница анкеты. Проверка таблицы с добавленными данными
        WebElement tableRow = driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr"));
        Assert.assertTrue(tableRow.getText().contains("user@example.com"));
        Assert.assertTrue(tableRow.getText().contains("User"));
        Assert.assertTrue(tableRow.getText().contains("Мужской"));
        Assert.assertTrue(tableRow.getText().contains("1.1"));
        Assert.assertTrue(tableRow.getText().contains("1.2"));
        Assert.assertTrue(tableRow.getText().contains("2.3"));

        driver.quit();
    }
}
