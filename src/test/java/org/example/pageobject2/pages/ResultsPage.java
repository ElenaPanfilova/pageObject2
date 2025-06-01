package org.example.pageobject2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultsPage {

    private static final String RESULTS_XPATH = "//a[contains(@class ,'tilk')]";
    private final WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
}

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(xpath = RESULTS_XPATH)
    private List <WebElement> results;

    public void waitResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(By.xpath("//a[contains(@class ,'tilk')]"), "href", "selenium"),
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class ,'tilk')]"))
        ));
    }

    public void clickElement(int num) {
        results.get(num).click();
        System.out.println("Нажатие на результат под номером " + num);
    }

    public String getTextFromSearchField(){
        String val = searchField.getAttribute("value");
        System.out.println("В строке поиска текст: " + val);
        return val;
    }
}
