package qtriptest.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    RemoteWebDriver driver;
    
    public AdventurePage(RemoteWebDriver driver){
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    @FindBy(xpath = "//select[@id='duration-select']")
    WebElement filtervalue;
    @FindBy(xpath = "//select[@id='category-select']")
    WebElement category;
    @FindBy(xpath="//input[@id='search-adventures']")
    WebElement adventure;
    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    WebElement clearfilter;
    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    WebElement clearcategory;
    @FindBy(xpath ="//div[@onclick='resetAdventuresData()']")
    WebElement clearadventure;
    @FindBy(xpath = "//div[@id='data']//a")
    WebElement count;

    public void  SetFilterValue(String DurationFilter) throws InterruptedException{
        filtervalue.click();
        Thread.sleep(2000);
        Select select = new Select(filtervalue);
        select.selectByVisibleText(DurationFilter);
    }

    public void  SetCategoryValue(String Category_Filter){
        category.click();
        Select select = new Select(category);
        select.selectByVisibleText(Category_Filter);
    }

    public void  getResultCount(String expectedFilteredResults){
        int i=driver.findElements(By.xpath("//div[@id='data']//a")).size();
        expectedFilteredResults =Integer.toString(i);
    }
    
    public void  selectAdventure(String adventureName) throws InterruptedException{
         adventure.sendKeys(adventureName);
         Thread.sleep(5000);
         List<WebElement> suggestions=driver.findElements(By.xpath("//div[@id='data']//a"));
         suggestions.get(0).click();
    }

    public void  clear() {
        clearfilter.click();
        clearcategory.click();
        clearadventure.click();
    }
}