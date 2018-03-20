package cz.neoris.smokesuite.pages;

import cz.neoris.smokesuite.Helper;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
    /*@FindBy(css="tr > th:nth-of-type(1) > span:nth-of-type(1)") private WebElement Filter_OrderNo;
    @FindBy(css="tr > th:nth-of-type(2) > span:nth-of-type(1)") private WebElement Filter_Submitted;
    @FindBy(css="tr > th:nth-of-type(3) > span:nth-of-type(1)") private WebElement Filter_Location;
    @FindBy(css="tr > th:nth-of-type(4) > span:nth-of-type(1)") private WebElement Filter_PurchaseOrderNumber;
    @FindBy(css="tr > th:nth-of-type(5) > span:nth-of-type(1)") private WebElement Filter_Products;
    @FindBy(css="tr > th:nth-of-type(6) > span:nth-of-type(1)") private WebElement Filter_Status;*/
    @FindBy(css="div.cmx-title-section > div:nth-of-type(1) > div") private WebElement Left_frame_customer_name;
    @FindBy(css="div.cmx-title-section > div:nth-of-type(1) > span") private WebElement Left_frame_customer_id;


    public void CheckLastPaginationNumber() {
        try {
            WebDriverWait nwait = new WebDriverWait(driver,30);
            WebElement LastPageNo = nwait.until(ExpectedConditions
                    .elementToBeClickable(By.cssSelector("ul.pagination > li:nth-of-type(5) > a.pagination__item-elem")));
            LastPageNo.click();
        } catch (ElementNotVisibleException e) {
            Assert.fail("Last page number in pagination not visible" + e );


        }

        try {
            WebDriverWait swait = new WebDriverWait(driver, 30);
            WebElement Order0119164309 = swait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr.zebra > td.order-code.title")));

            if (!Order0119164309.getText().equals("0119164309")) {
                Assert.fail("Order element found, but the number is not correct, number is " + Order0119164309.getText());
            }
        } catch (Exception w) {
            Assert.fail("Order element 0119162166 not found");

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

}
