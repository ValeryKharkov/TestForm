import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestForm {
    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        driver.get("file:/home/valery/IdeaProjects/TestForm/src/main/resources/qa-test.html");
    }
}
