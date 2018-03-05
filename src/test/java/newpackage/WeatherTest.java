package newpackage;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import newpackage.Page;
import resources.base;

public class WeatherTest extends base {
	
	@BeforeMethod
	public void initialaze() throws IOException {
		driver = initiaslazeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void terDown (){
		driver.manage().deleteAllCookies();
		driver.close();
		driver=null;
	}
	
	@Test
	public void cityMatchUiandAPI() 
	{
		driver.get(Page.aplicationUrl);
		Page page= new Page(driver);
		page.typeAddress(Page.city);
		page.clickButton();
		page.verifyUiAddress();
	}
	
	@Test
	public void todayDateMatchUiandAPI() 
	{
		driver.get(Page.aplicationUrl);
		Page page= new Page(driver);
		page.typeAddress(Page.city);
		page.clickButton();
		page.verifyUiTodayDate();
	}
	
	@Test
	public void temperatureMatchUiandAPI() 
	{
		driver.get(Page.aplicationUrl);
		Page page= new Page(driver);
		page.typeAddress(Page.city);
		page.clickButton();
		page.verifyUiTodayTemperature();
	}
	
	@Test
	public void summaryMatchUiandAPI() 
	{
		driver.get(Page.aplicationUrl);
		Page page= new Page(driver);
		page.typeAddress(Page.city);
		page.clickButton();
		page.verifyUiTodaySummary();
	}
	
	@Test
	public void correctCountOfDetails() 
	{
		driver.get(Page.aplicationUrl);
		Page page= new Page(driver);
		page.typeAddress(Page.city);
		page.clickButton();
		page.verifyDetailsCount();
	}
	
	@Test
	public void verifyIncorectData()
	{
		driver.manage().window().maximize();
		driver.get(Page.aplicationUrl);	
		Page page= new Page(driver);
		page.typeAddress("...");
		page.clickButton();
		page.verifyUiAddress();
		
	}
}
