package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class AdventureDetailsPage {
    RemoteWebDriver driver;
    
    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    @FindBy(xpath = "//input[@type='text']")
    WebElement name;
    @FindBy(xpath = "//input[@type='date']")
    WebElement date;
    @FindBy(xpath ="//input[@type='number']")
    WebElement persons;
    @FindBy(xpath="//button[@type='submit']")
    WebElement reserve;
    @FindBy(xpath = "//div[@class='alert alert-success']")
    WebElement bookingSuccessful;
    
    public void bookAdventure(String user, String bookingDate,String number) {
        name.sendKeys(user);
        date.sendKeys(bookingDate);
        persons.clear();
        persons.sendKeys(number);
        reserve.click();
    }

    public void isBookingSuccessful(){
        Assert.assertTrue(bookingSuccessful.isDisplayed(),"booking unsuccessful");
    }
}