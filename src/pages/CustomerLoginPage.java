package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerLoginPage {
    private  WebDriver driver;
    private final WebDriverWait wait;

    // WebElements using @FindBy annotations
    @FindBy(css = "button.btn.btn-primary.btn-lg[ng-click='customer()']")
    private WebElement customerLoginBtn;

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    }
    // Method to perform click action on Customer Login button
    public void clickCustomerLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(customerLoginBtn)).click();
    }
}