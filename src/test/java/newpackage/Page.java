package newpackage;

import static org.testng.Assert.assertEquals;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import newpackage.DemoAPI;
import resources.base;

public class Page  extends base {

static String city= prop.getProperty("city");
static String aplicationUrl= prop.getProperty("aplicationUrl");
static String apiUrl= prop.getProperty("apiUrl");


	
WebDriver driver;

	By address = By.id("address");
	By button = By.cssSelector("button.btn.btn-primary");
	By renderedAddress = By.xpath("//*[@id=\"renderedAddress\"]");
	By uiTodayDate = By.xpath("//div[@class='forecast-detail']/div[1]/h4[1]");
	By uiTodayTemperature = By.xpath("//div[@class='forecast-detail']/div[1]/h3");
	By uiTodaySummary = By.xpath("//div[@class='forecast-detail']/div[1]/h4[2]");
	By uiAllDetails = By.xpath("//div[@class='detail']");

	String uiAddress;
	String uiTodayDateVar;
	String uiTodayTemperatureVar;
	String uiTodaySummaryVar;
	
		
	public Page(WebDriver driver)
	{
		this.driver = driver;
	}
	public void typeAddress(String text)
	{
		driver.findElement(address).sendKeys(text);
	}
	public void clickButton()
	{
		driver.findElement(button).click();
	}
	public void verifyDetailsCount()
	{
		int count= driver.findElements(uiAllDetails).size();
		Assert.assertTrue(count==8); 
		System.out.println(count+" Items on the page");
	}
	
	public void verifyUiAddress()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='forecast-detail']/div[1]/h4[1]")));
		String  uiAddress = driver.findElement(renderedAddress).getText();
		DemoAPI api = new DemoAPI();
		assertEquals(uiAddress, api.getApiAdress());
		System.out.println("Address is verified");
	}
	public void verifyUiTodayDate()
	{
		String uiTodayDateVar = driver.findElement(uiTodayDate).getText();
		long epoch = System.currentTimeMillis()/1000;
		//System.out.println(epoch);
		String engTime = GetHumanReadableDate(epoch, "M/d/yyyy");
		//System.out.println(engTime);
		assertEquals(uiTodayDateVar,engTime);
		System.out.println("Date is verified");
	}
	public void verifyUiTodayTemperature()
	{
		String uiTodayTemperatureVar = driver.findElement(uiTodayTemperature).getText();
		uiTodayTemperatureVar = uiTodayTemperatureVar.substring(0,uiTodayTemperatureVar.length()-2);
		DemoAPI api = new DemoAPI();
		assertEquals(uiTodayTemperatureVar, api.getTodayTemperature());
		System.out.println("Temperature is verified");
	}
	public void verifyUiTodaySummary()
	{
		String uiTodaySummaryVar = driver.findElement(uiTodaySummary).getText();
		DemoAPI api = new DemoAPI();
		assertEquals(uiTodaySummaryVar, api.getTodaySummary());
		System.out.println("Summary is verified");
	}
	public static String GetHumanReadableDate(long epochSec, String dateFormatStr) {
	    Date date = new Date(epochSec * 1000);
	    SimpleDateFormat format = new SimpleDateFormat(dateFormatStr,Locale.getDefault());
	    return format.format(date);
	}
}