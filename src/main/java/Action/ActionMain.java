package Action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Readers.CredentialsReader;
import Readers.SpecsReader;
import model.locators;

public class ActionMain {
public static CredentialsReader creader;
public static SpecsReader sreader;
	static WebDriver driver;

	public ActionMain() throws IOException {
		creader = new CredentialsReader();
		sreader = new SpecsReader();

	}

	public  WebDriver NavigateToSite() {
		System.setProperty("webdriver.chrome.driver","C:\\\\\\\\Users\\\\manusharma\\\\Downloads\\\\chromedriver_win32\\\\chromedriver.exe");
	     driver = new ChromeDriver();
	     driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get(creader.getCredentialObj().getUrl());
		  driver.manage().window().maximize() ;
    	return driver;
	}

	public void Login( String username, String password) {
		locators loc = sreader.getObjByElement("login_panel");
		getWebElement(loc).click();
	     loc = sreader.getObjByElement("login_username");
		getWebElement(loc).sendKeys(username);
		loc = sreader.getObjByElement("login_password");
		getWebElement(loc).sendKeys(password);
		loc = sreader.getObjByElement("login_button");
	    getWebElement(loc).click();
	}
public static int getTokenTime(String token)
{
	DateTime today = DateTime.now();
	DateTime sameDayLastWeek = today.minusWeeks(1);
	DateTime mondayLastWeek = sameDayLastWeek.withDayOfWeek(DateTimeConstants.MONDAY);
Calendar calendar=	mondayLastWeek.toCalendar(Locale.ENGLISH);
	if(token.equalsIgnoreCase("date"))
	{
	return calendar.get(Calendar.DATE);	
	} else
	 {
		return calendar.get(Calendar.MONTH)+1;
	}
}
	public static WebElement getWebElement(locators loc) {
		WebElement element;
		String type = loc.getLocatorType();
		if (type.equals("css")) {
			element = driver.findElement(By.cssSelector(loc.getLocatorValue()));
		} else {

			element = driver.findElement(By.xpath(loc.getLocatorValue()));
		}
		return element;

	}
	public static WebElement getWebElement(locators loc,String date) {
		WebElement element;
		String type = loc.getLocatorType();
		if (type.equals("css")) {
			element = driver.findElement(By.cssSelector(loc.getLocatorValue(date)));
		} else {

			element = driver.findElement(By.xpath(loc.getLocatorValue(date)));
		}
		return element;

	}
	public static WebElement getWebElement(locators loc,String date,String leaveType) {
		WebElement element;
		String type = loc.getLocatorType();
		if (type.equals("css")) {
			element = driver.findElement(By.cssSelector(loc.getLocatorValue(date,leaveType)));
		} else {

			element = driver.findElement(By.xpath(loc.getLocatorValue(date,leaveType)));
		}
		return element;

	}
	public static float getTotalWeekTime(Boolean status)
	{  int date = getTokenTime("date");
       locators loc = sreader.getObjByElement("day");
       float a=0;
       for(int i =date;i<date+5;i++)
       {
    	  a = a+convertStringToInteger(getWebElement(loc,i+"").getText(),status);
       }
      int b =(int)a;
      b= (int)( (a-b)*100);
      if(b>60)
      {
    	  a = (float) (((int)a) + 1+(b%60)/100.0);
      }
		return a;
		
	}
	
  public static float convertStringToInteger(String dataTime,Boolean status)
  { 
	  String[] time= new String[2];
	  if(dataTime.contains("|"))
  {  StringTokenizer tokens = new StringTokenizer(dataTime, "|");
	time[0]=tokens.nextToken();
	time[1] =tokens.nextToken();
  } else{
    time[0]=dataTime;
   time[1]="00:00";
  } if(status==false){
String[] ttime = time[0].split(":");
  float a = (float)(Integer.parseInt(ttime[0].trim()));
  a = (float) (a+Integer.parseInt(ttime[1].trim())/100.0);

  return a;
  } else {
	  String[] ttime = time[1].split(":");
	  float a = Integer.parseInt(ttime[0].trim());
	  a= (float) (a+Integer.parseInt(ttime[1].trim())/100.0);
	  
	    return a;

	  
  }
  }
  
}
