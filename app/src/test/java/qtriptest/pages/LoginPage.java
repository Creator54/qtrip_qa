// package qtriptest.pages;

// import java.util.concurrent.TimeUnit;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.FindBy;
// import org.openqa.selenium.support.PageFactory;
// import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

// public class LoginPage {

//     RemoteWebDriver driver;

//     @FindBy(id="floatingInput")
//     WebElement emailInput;

//     @FindBy(id="floatingPassword")
//     WebElement passwordInput;

//     @FindBy(xpath="//button[text()='Login to QTrip']")
//     WebElement loginBtn;

//     // @FindBy(xpath ="//input[@type='email']")
//     // WebElement emailInput;
//     // @FindBy(xpath ="//input[@type='password']")
//     // WebElement passwordInput;
//     // @FindBy(xpath = "//button[@type='submit']")
//     // WebElement loginBtn;

//     public LoginPage(RemoteWebDriver driver) {
//         this.driver = driver;
//         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
//     }

//     public void performLogin(String emailId, String password) throws InterruptedException {
//         emailInput.sendKeys(emailId);
//         passwordInput.sendKeys(password);
//         Thread.sleep(2000);
//         loginBtn.click();
//     }
// }
package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class LoginPage {
    RemoteWebDriver driver;
    @FindBy(xpath ="//input[@type='email']")
    WebElement email;
    @FindBy(xpath ="//input[@type='password']")
    WebElement password;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;
    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutBtn;
    
    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }
    public void navigateToLoginpage(){
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/login/");
    }
     
    public void performLogin(String username,String pwd) throws InterruptedException {
        email.sendKeys(username);
        password.sendKeys(pwd);
        Thread.sleep(2000);
        loginBtn.click();
        Thread.sleep(5000);
        Assert.assertTrue(logoutBtn.isDisplayed(),"unable to login");
    }
}