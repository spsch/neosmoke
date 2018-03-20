package cz.neoris.smokesuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestGetNodeResolution implements Helper {

    private WebDriver driver;

    private Logger LOG = Logger.getLogger(String.valueOf(TestSmokeSuite.class));

    @BeforeClass
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TestGetResolution(){
        driver.get(QA_CEMEXGO_URL);
        LOG.info("Window height is: " + driver.manage().window().getSize().getHeight());
        LOG.info("Window width is: " + driver.manage().window().getSize().getWidth());
        LOG.info("Window position is: " + driver.manage().window().getPosition());
    }

    @AfterTest
    public void TestGetElements(){
        driver.close();
        driver.quit();


    }



}
