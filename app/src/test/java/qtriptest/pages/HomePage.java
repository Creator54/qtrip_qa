// package qtriptest.pages;

// import java.util.concurrent.TimeUnit;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.FindBy;
// import org.openqa.selenium.support.PageFactory;
// import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
// import org.testng.Assert;

// public class HomePage {
//     RemoteWebDriver driver;

//     @FindBy(xpath = "//a[text()='Login Here']")
//     WebElement loginBtn;

//     @FindBy(xpath = "//div[text()='Logout']")
//     WebElement logoutBtn;

//     @FindBy(xpath = "//a[text()='Register']")
//     WebElement registerBtn;

//     public HomePage(RemoteWebDriver driver) {
//         this.driver=driver;
//         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//         PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
//     }

//     public void navigateToHomePage() throws InterruptedException{
//         driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
//     }

//     public void navigateToRegisterPage() throws InterruptedException{
//         registerBtn.click();
//     }

//     public void navigateToLoginPage() throws InterruptedException{
//         loginBtn.click();
//     }

//     public Boolean isUserLoggedIn(){
//         return logoutBtn.isDisplayed();
//     }

//     public Boolean searchCity(String city) throws InterruptedException{
//         Boolean status = false;
//         WebElement search =  driver.findElement(By.xpath("//*[@id='autocomplete']"));
//         search.sendKeys(city);
//         Thread.sleep(3000);
//         if(driver.findElement(By.xpath("//*[@id='results']/h5")).isDisplayed()){
//             status = false;
//             search.clear();
//         }
//         else driver.findElement(By.id(city.toLowerCase())).click();

//         return status;
//     }

//     public void performLogOut(){
//         logoutBtn.click();
//     }
// }
package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class HomePage {
    RemoteWebDriver driver;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement register;
    @FindBy(xpath = "//a[text()='Login Here']")
    WebElement loginBtn;
    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutBtn;
    @FindBy(xpath="//button[text()='Register Now']")
    WebElement registerNow;
    @FindBy(xpath = "//input[@class='hero-input']")
    WebElement searchCity;
    @FindBy(xpath = "//*[text()='No City found']")
    WebElement noCityFound;
    @FindBy(xpath="//ul[@id='results']")
    WebElement autoText;

    public HomePage(RemoteWebDriver driver) {
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //PageFactory.initElements(driver,this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);

    }
    public void logOut(){
        logoutBtn.click();
        Assert.assertTrue(loginBtn.isDisplayed(), "unable to logout");
    }
    public void navigateToHomepage(){
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");

    }
    public void navigateToRegisterpage(){
        register.click();
        Assert.assertTrue(registerNow.isDisplayed(),"unable to navigate to register page");
    }
    public void SearchCity(String city) throws InterruptedException{
        searchCity.sendKeys(city);
        Thread.sleep(2000);
        Assert.assertTrue(noCityFound.isDisplayed(),"city found");
        searchCity.clear();
    }
    public void assertAutoCompleteText(String city) throws InterruptedException {
        searchCity.sendKeys(city);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a/li")).click();
     }
    public void selectCity(String city){
        autoText.findElement(By.id(city)).click();
    }
}