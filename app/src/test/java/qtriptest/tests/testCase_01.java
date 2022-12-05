package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_01 {
    //verify userRegistration
    static RemoteWebDriver driver;

    @BeforeTest(alwaysRun = true)
    public static void setDriver() throws MalformedURLException{
        driver = DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
    }

    @Test(description = "User Onboarding Flow",priority = 1,dataProvider = "dataProvider",dataProviderClass = DP.class, groups="group1")
    public static void TestCase01(String email, String passwd) throws InterruptedException{
        HomePage home = new HomePage(driver);
        home.gotoHomePage();
        // System.out.println(driver.getCurrentUrl());
        // Thread.sleep(2000);
        home.navigateToRegisterPage();
        // Thread.sleep(2000);

        RegisterPage registration = new RegisterPage(driver);
        // String email = "testmail@gmail.com";
        // String passwd = "abc@1234";
        registration.registerUser(email, passwd, passwd, true);
        Thread.sleep(5000);
        String lastGeneratedUserEmail = registration.lastGeneratedUserEmail;
        // System.out.println(lastGeneratedUserEmail);

        // home.navigateToLoginPage();//not needed as after registration page is redirected to login page
        // Thread.sleep(5000);
        LoginPage login = new LoginPage(driver);
        login.performLogin(lastGeneratedUserEmail, passwd);
        Thread.sleep(5000);

        if(home.isUserLoggedIn()){
            // System.out.println("User successfully logged in with id: "+ lastGeneratedUserEmail);
            home.performLogOut();
            // System.out.println("User successfully logged out with id: "+ lastGeneratedUserEmail);
        }
        else{
            System.out.println("Failed to successfully login User !");
        }
    }

    @AfterSuite
    public static void quitDriver(){
        driver.quit();
    }

    // public static void main(String[] args) throws InterruptedException, MalformedURLException {
    //     setDriver();
    //     TestCase01();
    //     quitDriver();
    // }
    //login
    //logout
}
