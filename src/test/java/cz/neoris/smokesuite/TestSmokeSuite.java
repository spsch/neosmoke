package cz.neoris.smokesuite;

import cz.neoris.smokesuite.pages.LoginPageBy;
import cz.neoris.smokesuite.pages.OrderHistoryBy;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestSmokeSuite {

    //initialize driver
    private WebDriver driver;

    //initialize logger
    private Logger LOG = Logger.getLogger(String.valueOf(TestSmokeSuite.class));

    @BeforeClass
    public void SetUpClass() {

        WebDriverManager.chromedriver().forceDownload();
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 0)
    public void TestCemexGo_Login_MX() throws InterruptedException {

        LOG.log(Level.INFO, "Start MX log in test");
        LoginPageBy loginpage = new LoginPageBy(driver).get();
        loginpage.LogInMX();
    }

    @Test(priority = 1)
    public void TestCemexGo_Orders_History_MX() throws InterruptedException {
        LOG.info("Orders history page check");
        OrderHistoryBy ohb = new OrderHistoryBy(driver);
        ohb.CheckLastPaginationNumber();
        captureScreen();
        ohb.CheckOrderTableHeader();
        ohb.CheckLegalEntities();
    }

    @Test (priority = 2)
    public void TestCemexGo_Logout() {
        LOG.info("Logout");
        OrderHistoryBy ohb = new OrderHistoryBy(driver);
        ohb.Logout();
    }



    public String captureScreen() {
        String path;

        try {
            WebDriver augdriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)augdriver).getScreenshotAs(OutputType.FILE);
            path = "C:\\Users\\jan.svehlak\\code\\SmokeSuite\\src\\test\\java\\cz\\neoris\\smokesuite\\screenshots" + source.getName();
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            path = "Fail taking screenshot" + e;
        }

        return path;
    }

}
