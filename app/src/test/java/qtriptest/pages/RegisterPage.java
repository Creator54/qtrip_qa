// package qtriptest.pages;

// import java.util.UUID;
// import java.util.concurrent.TimeUnit;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.FindBy;
// import org.openqa.selenium.support.PageFactory;
// import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

// public class RegisterPage {
    
//     RemoteWebDriver driver;
//     public String lastGeneratedUserEmail = "";

//     @FindBy(id = "floatingInput")
//     WebElement emailInput;

//     @FindBy(id = "floatingPassword")
//     WebElement passwordInput;

//     @FindBy(xpath = "//input[@placeholder='Retype Password to Confirm']")
//     WebElement confirmpasswordInput;

//     @FindBy(xpath = "//button[text()='Register Now']")
//     WebElement registerBtn;

//     public RegisterPage(RemoteWebDriver driver) throws InterruptedException {
//         this.driver = driver;
//         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
//     }

//     public void registerUser(String emailId, String password, String confirmPassword, Boolean makeDynamic) throws InterruptedException {
//         if (makeDynamic) {
//             emailId = UUID.randomUUID().toString()+ emailId;
//             this.lastGeneratedUserEmail = emailId;
//         }
//         emailInput.sendKeys(emailId);
//         passwordInput.sendKeys(password);
//         confirmpasswordInput.sendKeys(confirmPassword);
//         registerBtn.click();
//     }
// }
package qtriptest.pages;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class RegisterPage {
    RemoteWebDriver driver;
    public String generatedUsername;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement register;
    @FindBy(xpath="//button[text()='Register Now']")
    WebElement registerNow;
    @FindBy(xpath="//input[@name='email']")
    WebElement email;
    @FindBy(xpath="//input[@name='password']")
    WebElement password;
    @FindBy(xpath="//input[@name='confirmpassword']")
    WebElement confirmpassword;
    @FindBy(xpath="//button[text()='Login to QTrip']")
    WebElement loginToQtrip;

    public RegisterPage(RemoteWebDriver driver){
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    
    public void registerUser(String username, String pwd, Boolean makeUsernameDynamic) throws InterruptedException{
        if(makeUsernameDynamic){
            username=username+UUID.randomUUID().toString();
            this.generatedUsername=username;
        }
        email.sendKeys(username);
        password.sendKeys(pwd);
        confirmpassword.sendKeys(pwd);
        registerNow.submit();
        Thread.sleep(2000);
        Assert.assertTrue(loginToQtrip.isDisplayed(),"unable to register");
    }
}