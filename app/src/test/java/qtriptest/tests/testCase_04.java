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

public class testCase_04 {
    static RemoteWebDriver driver;

    @BeforeTest()
    public void createDriver() throws MalformedURLException{
        driver = DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
    }

    @Test(description = "Ticket Booking History",priority = 4,dataProvider = "dataProvider",dataProviderClass = DP.class,groups="group4")

    public void TestCase04(String NewUserName,String Password,String dataset1,String dataset2,String dataset3) throws InterruptedException
    {   
        String[] DS1 = dataset1.split(";");
        String[] DS2 = dataset2.split(";");
        String[] DS3 = dataset3.split(";");
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
        objhome.assertAutoCompleteText(DS1[0]);
        Thread.sleep(5000);
        AdventurePage objadv = new AdventurePage(driver);
        objadv.selectAdventure(DS1[1]);
        Thread.sleep(5000);
        AdventureDetailsPage objadvd = new AdventureDetailsPage(driver);
        objadvd.bookAdventure(DS1[2],DS1[3],DS1[4]);
        Thread.sleep(5000);
        objadvd.isBookingSuccessful();
        Thread.sleep(5000);
        objhome.navigateToHomepage();
        Thread.sleep(2000);
        objhome.assertAutoCompleteText(DS2[0]);
        Thread.sleep(5000);
        objadv.selectAdventure(DS2[1]);
        Thread.sleep(5000);
        objadvd.bookAdventure(DS2[2],DS2[3],DS2[4]);
        Thread.sleep(5000);
        objadvd.isBookingSuccessful();
        Thread.sleep(5000);
        objhome.navigateToHomepage();
        Thread.sleep(2000);
        objhome.assertAutoCompleteText(DS3[0]);
        Thread.sleep(5000);
        objadv.selectAdventure(DS3[1]);
        Thread.sleep(5000);
        objadvd.bookAdventure(DS3[2],DS3[3],DS3[4]);
        Thread.sleep(5000);
        objadvd.isBookingSuccessful();
        Thread.sleep(5000);
        HistoryPage objhis = new HistoryPage(driver);
        objhis.GetReservations();
        Thread.sleep(5000);
        objhome.logOut();
        Thread.sleep(5000);
            
        }
        @AfterSuite()
        public void tearDown(){
            driver.quit();
        }
    }