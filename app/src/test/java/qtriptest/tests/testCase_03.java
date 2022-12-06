package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 {
    static RemoteWebDriver driver;

    @BeforeTest()
    public void createDriver() throws MalformedURLException{
        driver = DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
    }
    
    @Test(description = "Ticket Booking and Cancellation",priority = 3,dataProvider = "dataProvider",dataProviderClass = DP.class,groups="group3")
    
    public void TestCase03(String NewUserName,String Password,String SearchCity,String AdventureName,String GuestName,String Date,String count)throws InterruptedException{
        HomePage objhome = new HomePage(driver);
        objhome.navigateToHomepage();
        Thread.sleep(2000);
        objhome.navigateToRegisterpage();
        Thread.sleep(2000);
        RegisterPage objregister = new RegisterPage(driver);
        objregister.registerUser(NewUserName,Password, true);
        Thread.sleep(2000);
        LoginPage objlogin = new LoginPage(driver);
        objlogin.performLogin(objregister.generatedUsername,Password);
        objhome.assertAutoCompleteText(SearchCity);
        Thread.sleep(2000);
        AdventurePage objadv = new AdventurePage(driver);
        objadv.selectAdventure(AdventureName);
        Thread.sleep(5000);
        AdventureDetailsPage objadvd = new AdventureDetailsPage(driver);
        objadvd.bookAdventure(GuestName, Date, count);
        Thread.sleep(5000);
        objadvd.isBookingSuccessful();
        Thread.sleep(2000);
        HistoryPage objhis = new HistoryPage(driver);
        objhis.GetReservations();
        Thread.sleep(5000);
        objhis.CancelReservations();
        Thread.sleep(5000);
        objhome.logOut();

}
        @AfterSuite()
        public void tearDown(){
            driver.quit();
        }
}