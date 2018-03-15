package cz.neoris.smokesuite.pages;

import cz.neoris.smokesuite.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Logger;

public class OrderHistoryMethodsBy implements Helper {

    private final WebDriver driver;

    private final static Logger LOG_OH = Logger.getLogger(String.valueOf(OrderHistoryMethodsBy.class));

    public OrderHistoryMethodsBy(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //definition of elements
    @FindBy(css="tr > th:nth-of-type(1) > span:nth-of-type(1)") private WebElement Filter_OrderNo;
    @FindBy(css="tr > th:nth-of-type(2) > span:nth-of-type(1)") private WebElement Filter_Submitted;
    @FindBy(css="tr > th:nth-of-type(3) > span:nth-of-type(1)") private WebElement Filter_Location;
    @FindBy(css="tr > th:nth-of-type(4) > span:nth-of-type(1)") private WebElement Filter_PurchaseOrderNumber;
    @FindBy(css="tr > th:nth-of-type(5) > span:nth-of-type(1)") private WebElement Filter_Products;
    @FindBy(css="tr > th:nth-of-type(6) > span:nth-of-type(1)") private WebElement Filter_Status;
    @FindBy(css="div.cmx-title-section > div:nth-of-type(1) > div") private WebElement Left_frame_customer_name;
    @FindBy(css="div.cmx-title-section > div:nth-of-type(1) > span") private WebElement Left_frame_customer_id;

    public boolean SearchFilterName(WebDriver driver, String header){
        boolean isFound = false;

        List<WebElement> rows = driver.findElements(By.cssSelector("#table > tbody > tr:nth-child(1)"));

        for (WebElement row : rows) {
            if(row.findElement(By.cssSelector("tr > th:nth-of-type")).getText().equals(header)){
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public void CheckFilterNames() {
        boolean a = SearchFilterName(driver, "Order No");
        Assert.assertTrue(a, "Order No nenalezen");

    }

    public void CheckOrderTableHeader() {
        WebDriverWait twait = new WebDriverWait(driver, 30);
        WebElement order_table_header = twait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        List<WebElement> order_table_header_list = order_table_header.findElements(By.tagName("th"));
        Integer radky = order_table_header_list.size();
        LOG_OH.info("RADKY: " + radky);

        for (int i=0; i<radky; i++) {
            WebElement radek = order_table_header_list.get(i);
            String radek_text = radek.getText();
            LOG_OH.info("radky: " + radek_text);
        }
        boolean isHeaderPresent = Filter_OrderNo.isDisplayed();
        Assert.assertTrue(isHeaderPresent, "Order no filter is missing");
    }

    public void CheckOrderTable(){
        WebDriverWait table_wait = new WebDriverWait(driver, 60);
        WebElement order_table = table_wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        List<WebElement> order_table_rows = order_table.findElements(By.tagName("td"));
        List<WebElement> x_order_table_rows = order_table.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr/td"));

        Integer row_num = x_order_table_rows.size();
        LOG_OH.info("Rows: " + row_num);

        for (int i=0; i<row_num; i++) {
            WebElement row = x_order_table_rows.get(i);
            row.isEnabled();
            String row_text = row.getText();
            LOG_OH.info("Rows inhalt: " + row_text);
        }

        boolean isRMX = driver.getPageSource().contains("ReadyMix");
        Assert.assertTrue(isRMX, "RMX is not present");
    };


    public void Logout() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement RightUpperCircle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.circle-initials")));
        RightUpperCircle.click();
        WebElement LogOutlink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.cmx-call-to-action > a:nth-of-type(2)")));
        LogOutlink.click();
    }

}
