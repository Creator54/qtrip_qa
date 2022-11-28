package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    RemoteWebDriver driver;

    @FindBy(xpath = "//a[text()='Login Here']")
    WebElement loginBtn;

    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutBtn;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerBtn;

    public HomePage(RemoteWebDriver driver) {
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public void gotoHomePage() throws InterruptedException{
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
    }

    public void navigateToRegisterPage() throws InterruptedException{
        registerBtn.click();
    }

    public void navigateToLoginPage() throws InterruptedException{
        loginBtn.click();
    }

    public Boolean isUserLoggedIn(){
        return logoutBtn.isDisplayed();
    }

    public void performLogOut(){
        logoutBtn.click();
    }
}
