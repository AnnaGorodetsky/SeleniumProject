package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "userSelect")
    private WebElement userSelectDropdown;

    @FindBy(css = "button.btn.btn-default")
    private WebElement loginBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    }

    public void selectCustomer(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(userSelectDropdown));
        Select select = new Select(userSelectDropdown);
        select.selectByValue(value);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
