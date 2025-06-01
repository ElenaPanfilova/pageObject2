package org.example.pageobject2.tests;

import org.example.pageobject2.pages.MainPage;
import org.example.pageobject2.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.ArrayList;

public class BingSearchTest {
    public static String INPUT = "Selenium";
    private WebDriver driver;
    MainPage mp;
    ResultsPage rp;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
        mp = new MainPage(driver);
        rp = new ResultsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void searchResultsTest() {
        mp.sendText(INPUT);
        rp.waitResults();
        rp.clickElement(0);
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        if (tabs.size() > 1) driver.switchTo().window(tabs.get(1));
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Открылась неверная ссылка");
    }

    @Test
    public void searchFieldTest() {
        mp.sendText(INPUT);
        assertEquals(INPUT, rp.getTextFromSearchField(), "Текст не совпал");
    }
}