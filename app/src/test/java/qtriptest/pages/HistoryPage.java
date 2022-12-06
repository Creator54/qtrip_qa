package qtriptest.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class HistoryPage {
    RemoteWebDriver driver;

    public HistoryPage(RemoteWebDriver driver) {
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    @FindBy(xpath = "//a[text()='Reservations']")
    WebElement history;
    @FindBy(xpath = "//tbody//tr//th")
    WebElement  transaction;
    @FindBy(xpath = "//button[@class='cancel-button']")
    WebElement cancel;
    @FindBy(xpath ="//div[@id='no-reservation-banner']")
    WebElement noReservation;
    
    public void GetReservations(){
        history.click();
        List<WebElement> transactionId = driver.findElements(By.xpath("//tbody//tr//th"));
        transactionId.get(0).getText();
    }

    public void CancelReservations() throws InterruptedException{
        cancel.click();
        Thread.sleep(5000);
        Assert.assertTrue(noReservation.isDisplayed(),"reserved");
    }
}