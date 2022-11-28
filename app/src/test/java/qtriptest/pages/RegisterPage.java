package qtriptest.pages;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {
    
    RemoteWebDriver driver;
    public String lastGeneratedUserEmail = "";

    @FindBy(id = "floatingInput")
    WebElement emailInput;

    @FindBy(id = "floatingPassword")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@placeholder='Retype Password to Confirm']")
    WebElement confirmpasswordInput;

    @FindBy(xpath = "//button[text()='Register Now']")
    WebElement registerBtn;

    public RegisterPage(RemoteWebDriver driver) throws InterruptedException {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void registerUser(String emailId, String password, String confirmPassword, Boolean makeDynamic) throws InterruptedException {
        if (makeDynamic) {
            emailId = UUID.randomUUID().toString()+ emailId;
            this.lastGeneratedUserEmail = emailId;
        }
        emailInput.sendKeys(emailId);
        passwordInput.sendKeys(password);
        confirmpasswordInput.sendKeys(confirmPassword);
        registerBtn.click();
    }
}
