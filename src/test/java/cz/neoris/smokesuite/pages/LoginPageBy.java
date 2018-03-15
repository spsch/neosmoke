package cz.neoris.smokesuite.pages;

import cz.neoris.smokesuite.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.jws.soap.SOAPBinding;

public class LoginPageBy extends LoadableComponent<LoginPageBy> implements Helper {

    private final WebDriver driver;

    public LoginPageBy(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void load() {
        driver.get(QA_CEMEXGO_URL);
    }

    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, QA_CEMEXGO_URL + "/ordersnproduct/login");
    }
    //definition of elements
    @FindBy(css = "div.welcome") private WebElement Welcomeheader;
    @FindBy(css = "[name=\"username\"]") private WebElement Username;
    @FindBy(css = "[name=\"password\"]") private WebElement Password;
    @FindBy(css = "button.button") private WebElement LoginBtn;

    //test methods
    private void ClearAndType(WebElement field, String text) {
        field.clear();
        field.sendKeys();
    }

    public void LogInMX() {
        Username.sendKeys(QA_CEMEXGO_MX);
        Password.sendKeys(QA_PWD);
        LoginBtn.click();
    }

    public void LogInUS_RMX(){
        Username.sendKeys(QA_CEMEXGO_US_RMX);
        Password.sendKeys(QA_PWD);
        LoginBtn.click();
    }

    public void LogInCustom(String CustomUsername){
        ClearAndType(Username, CustomUsername);
        ClearAndType(Password, QA_PWD);
        LoginBtn.click();
    }
}
