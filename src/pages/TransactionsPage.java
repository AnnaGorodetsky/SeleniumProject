package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TransactionsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "table.table.table-bordered.table-striped tbody")
    private WebElement tbody;

    @FindBy(css = "button.btn[ng-click='back()'][style='float:left']")
    private WebElement backBtn;

    @FindBy(css = "tr#anchor0.ng-scope")
    private WebElement creditElement;

    @FindBy(css = "tr#anchor1.ng-scope")
    private WebElement debitElement;

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isTransactionsEmpty() {
        wait.until(ExpectedConditions.visibilityOf(tbody));
        List<WebElement> childElements = tbody.findElements(By.xpath(".//*"));
        return childElements.isEmpty();
    }

    public void clickOnBackBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();
    }

    public void validateCredit(String expectedValueText, String expectedTypeText) {
        validateTransaction(creditElement, expectedValueText, expectedTypeText);
    }

    public void validateDebit(String expectedValueText, String expectedTypeText) {
        validateTransaction(debitElement, expectedValueText, expectedTypeText);
    }

    private void validateTransaction(WebElement transactionElement, String expectedValueText, String expectedTypeText) {
        WebElement valueElement = transactionElement.findElement(By.xpath("./td[2]"));
        WebElement typeElement = transactionElement.findElement(By.xpath("./td[3]"));

        String valueText = valueElement.getText();
        String typeText = typeElement.getText();

        validateAssertion(valueText, expectedValueText, "Value");
        validateAssertion(typeText, expectedTypeText, "Type");
    }

    private void validateAssertion(String actual, String expected, String attribute) {
        if (actual.equals(expected)) {
            System.out.println(attribute + " Assertion Passed: Actual " + attribute + " is '" + actual + "'");
        } else {
            System.out.println(attribute + " Assertion Failed: Actual " + attribute + " is '" + actual + "', Expected " + attribute + " was '" + expected + "'");
        }
    }
}