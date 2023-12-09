package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class WelcomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "button.btn.btn-lg.tab")
    private WebElement transactionsTab;

    @FindBy(css = "button.btn.btn-lg.tab[ng-click='deposit()']")
    private WebElement depositTab;

    @FindBy(css = "input[type='number'].form-control[ng-model='amount']")
    private WebElement numberInput;

    @FindBy(css = "button.btn.btn-default")
    private WebElement depositBtn;

    @FindBy(css = "span.error.ng-binding[ng-show='message']")
    private WebElement validationMessage;

    @FindBy(css ="button.btn.btn-lg.tab[ng-click='withdrawl()']")
    private WebElement withdrawlTab;

    @FindBy(css ="button.btn.btn-default")
    private WebElement withdrawlBtn;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    }

    public void clickOnTransactionsPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(transactionsTab)).click();
    }

    public void clickOnDepositTab(String credit) {

        wait.until(ExpectedConditions.elementToBeClickable(depositTab)).click();
        wait.until(ExpectedConditions.visibilityOf(depositBtn));
        numberInput.sendKeys(credit);
        depositBtn.click();
    }

    public void creditValidation(String expectedText) {
        String actualText = wait.until(ExpectedConditions.visibilityOf(validationMessage)).getText();
        assertEquals(actualText, expectedText, "Text does not match expected");
    }

    public void clickOnWithdrawlTab(String debit) {
        wait.until(ExpectedConditions.elementToBeClickable(withdrawlTab)).click();
        withdrawlTab.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        numberInput.sendKeys(debit);
        wait.until(ExpectedConditions.elementToBeClickable(withdrawlBtn)).click();

    }

    public void debitValidation(String expectedText) {
        String actualText = validationMessage.getText();
        assertEquals(actualText, expectedText, "Text does not match expected");
    }
}