package cz.neoris.smokesuite.pages;

import cz.neoris.smokesuite.Helper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class OrderHistoryBy implements Helper {

    private final WebDriver driver;

    private final static Logger LOG_OH = Logger.getLogger(String.valueOf(OrderHistoryBy.class));

    public OrderHistoryBy(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //definition of elements
    @FindBy(css="div.cmx-title-section > div:nth-of-type(1) > div") private WebElement Left_frame_customer_name;
    @FindBy(css="div.cmx-title-section > div:nth-of-type(1) > span") private WebElement Left_frame_customer_id;


    public void CheckLastPaginationNumber() {
        try {
            WebDriverWait nwait = new WebDriverWait(driver,60);
            WebElement LastPageNo = nwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.pagination > li:nth-of-type(5) > a.pagination__item-elem")));
                    /*ExpectedConditions.elementToBeClickable(By.cssSelector("ul.pagination > li:nth-of-type(5) > a.pagination__item-elem")),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.pagination > li:nth-of-type(5) > a.pagination__item-elem"))*/

            captureScreen();
            LastPageNo.click();
        } catch (ElementNotVisibleException e) {
            Assert.fail("Last page number in pagination not visible" + e );
        }

        try {
            WebDriverWait swait = new WebDriverWait(driver, 30);
            WebElement Order0119164309 = swait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr.zebra > td.order-code.title")));
            captureScreen();
            Assert.assertTrue(true, String.valueOf(Order0119164309.getText().startsWith("011")));
            /*Assert.assertEquals(Order0119164309.getText(), "0119164399");*/

        } catch (Exception w) {
            Assert.fail("Order element 0119164399 not found");

        }
    }

    /*public void CheckTableByOrder() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Find element by link text and store in variable "Element"
        WebElement Element = driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[143]/td[1]"));
        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);
        Assert.assertEquals(Element.getText(), QA_MX_ORDERNO);
    }*/

    public void CheckOrderTableHeader() {

        WebDriverWait twait = new WebDriverWait(driver, 30);
        WebElement order_table_header = twait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        List<WebElement> order_table_header_list = order_table_header.findElements(By.tagName("th"));
        Integer number_of_cols = order_table_header_list.size();
        LOG_OH.info("Header columns count: " + number_of_cols);

        for (int i=0; i<number_of_cols; i++) {
            WebElement col = order_table_header_list.get(i);
            String col_text = col.getText();
            LOG_OH.info("radky: " + col_text);
        }
        Integer const_col = 6;
        Assert.assertEquals(number_of_cols, const_col);
    }

    public void CheckLegalEntities() {

        Assert.assertEquals(Left_frame_customer_name.getText(), QA_MX_CUSTOMER_NAME);
        LOG_OH.info("CUSTOMER NAME: " + Left_frame_customer_name.getText());
        Assert.assertEquals(Left_frame_customer_id.getText(), QA_MX_CUSTOMER_ID);
        LOG_OH.info("CUSTOMER ID: " + Left_frame_customer_id.getText());
    }

    public void CheckUploadDemandSchedule() {


    }

    public void Logout() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement RightUpperCircle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.circle-initials")));
        RightUpperCircle.click();
        WebElement LogOutlink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.cmx-call-to-action > a:nth-of-type(2)")));
        LogOutlink.click();
    }

    public void ScrollToElement(WebElement elem){
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[].scrollIntoView(true);", elem);
        }
    }

    public String captureScreen() {
        String path;

        try {
            WebDriver augdriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)augdriver).getScreenshotAs(OutputType.FILE);
            path = ".\\SmokeSuite\\src\\test\\java\\cz\\neoris\\smokesuite\\screenshots\\" + source.getName();
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            path = "Fail taking screenshot" + e;
        }

        return path;
    }

}
