package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CustomerLoginPage;
import pages.HomePage;
import pages.TransactionsPage;
import pages.WelcomePage;

public class TestBankingApp {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lench\\Downloads\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/\n" +
                "BankingProject/#/login");

        //Click on ‘Customer Login’ button
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.clickCustomerLogin();

        //Choose ‘Harry Potter’ from list
        HomePage homePage = new HomePage(driver);
        homePage.selectCustomer("2");

        //Click ‘Login’
        homePage.clickLoginBtn();

        //Verification : there are no any ‘Transactions’
        WelcomePage welcomePage =new WelcomePage(driver);
        welcomePage.clickOnTransactionsPage();

        TransactionsPage transactionsPage = new TransactionsPage(driver);
        boolean isEmpty = transactionsPage.isTransactionsEmpty();
        assert isEmpty : "Transactions should be empty";

        //Go Back to previous page
        transactionsPage.clickOnBackBtn();

        //Enter the Deposit 200
        welcomePage.clickOnDepositTab("200");
        welcomePage.creditValidation("Deposit Successful");

        //Enter the Withdraw 100
        welcomePage.clickOnWithdrawlTab("100");
        welcomePage.debitValidation("Transaction successful");

        //Go to ‘Transactions’ page and verify the result
        welcomePage.clickOnTransactionsPage();

        transactionsPage.validateCredit("200","Credit");
        transactionsPage.validateDebit("100","Debit");


        driver.quit();

    }
}

