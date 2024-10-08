package org.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");

    }

    @AfterEach
    public void tearDown() { driver.quit();
    }

    @Test
    public void sciencePopTest() {
        WebElement userIcon = driver.findElement(By.cssSelector("a[href='/ru/flows/popsci/']"));
        userIcon.click();

        WebElement authorIcon = driver.findElement(By.xpath("//span[contains(@class, 'tm-tabs__tab-item')]//*[contains(text(), 'Авторы')]"));
        authorIcon.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Поиск')]")).isDisplayed(),
         "Поиск не найден");
    }

    @Test
    public void becomeAuthorTest() {
        WebElement authIcon = driver.findElement(By.xpath("//a[@class='tm-header__become-author-btn']"));
        authIcon.click();

        WebElement waitInv = driver.findElement(By.xpath("//a[@class='tm-tabs__tab-link tm-tabs__tab-link tm-tabs__tab-link_active']"));
        waitInv.click();

        assertTrue(driver.findElement(By.xpath("//h2[@class='tm-block__title tm-block__title']")).isDisplayed(),
        "О песочнице не обнаружено");
    }
}