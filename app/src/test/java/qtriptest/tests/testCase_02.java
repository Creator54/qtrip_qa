// package qtriptest.tests;

// import qtriptest.DP;
// import qtriptest.DriverSingleton;
// import qtriptest.pages.HomePage;
// import qtriptest.pages.LoginPage;
// import qtriptest.pages.RegisterPage;
// import java.net.MalformedURLException;
// import java.util.List;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.ui.Select;
// import org.testng.Assert;
// import org.testng.annotations.AfterSuite;
// import org.testng.annotations.BeforeTest;
// import org.testng.annotations.Test;

// public class testCase_02 {
//     static RemoteWebDriver driver;

//     @BeforeTest(alwaysRun = true)
//     public static void setDriver() throws MalformedURLException{
//         driver = DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
//     }

//     @Test(description = "User Onboarding Flow",priority = 1,dataProvider = "dataProvider",dataProviderClass = DP.class, groups="group1")
//     public static void TestCase02(String cityName, String categoryFilter, String durationFilter,String expectedFilteredResults, String expectedUnFilteredResults) throws InterruptedException{
//         HomePage home = new HomePage(driver);
//         home.navigateToHomePage();
//         home.searchCity(cityName);

//     }

//     @AfterSuite
//     public static void quitDriver(){
//         driver.quit();
//     }
// }
package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 {
    static RemoteWebDriver driver;

    @BeforeTest()
    public void createDriver() throws MalformedURLException{
        driver = DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
    }

    @Test(description = "Search & Filters",priority = 2,dataProvider = "dataProvider",dataProviderClass = DP.class, groups="group2")
    public void TestCase02(String CityName, String Category_Filter,String DurationFilter,String ExpectedFilteredResults,String ExpectedUnFilteredResults) throws InterruptedException
    {
        HomePage objhome = new HomePage(driver);
        objhome.navigateToHomepage();
        Thread.sleep(2000);
        // objhome.SearchCity(CityName);
        // Thread.sleep(2000);
        objhome.assertAutoCompleteText(CityName);
        Thread.sleep(2000);
        AdventurePage objadv = new AdventurePage(driver);
        //objadv.navigateToAdventurePage;
        Thread.sleep(2000);
        
        objadv.SetFilterValue(DurationFilter);
        //Thread.sleep(5000);
        objadv.SetCategoryValue(Category_Filter);
        //Thread.sleep(2000);
        objadv.getResultCount(ExpectedFilteredResults);
        //Thread.sleep(2000);
        objadv.clear();
        //Thread.sleep(2000);
        objadv.getResultCount(ExpectedUnFilteredResults);
    }

    @AfterSuite()
    public void tearDown(){
        driver.quit();
    }
}