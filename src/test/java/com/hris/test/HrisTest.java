package com.hris.test;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import Action.ActionMain;
import mx4j.log.Log;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Reporter;
import org.testng.annotations.*;


public class HrisTest {
	
	ActionMain obj;
	WebDriver driver;
	@BeforeTest
	public void loadClass() throws IOException
	{
	   obj = new ActionMain();
	   driver = obj.NavigateToSite();
	}
	
	
  @Test(priority=1)
   @Parameters({"username","password"})
  public void loginTest(@Optional("manusharma")String username,@Optional("Manu@9015")String password) throws InterruptedException {
     obj.Login(username, password);
    assertEquals(obj.getWebElement(obj.sreader.getObjByElement("title_left")).getText(),"Manu");
  }
  @Test(priority=2)
  public void testEmpDetails()
  {	  assertEquals(obj.getWebElement(obj.sreader.getObjByElement("designation")).getText(), obj.creader.getCredentialObj().getDesignation());
	  assertEquals(obj.getWebElement(obj.sreader.getObjByElement("rank")).getText(), obj.creader.getCredentialObj().getrank());
	  assertEquals(obj.getWebElement(obj.sreader.getObjByElement("YOE")).getText(), obj.creader.getCredentialObj().getYOE());
	  assertEquals(obj.getWebElement(obj.sreader.getObjByElement("PYE")).getText(), obj.creader.getCredentialObj().getPYE());

  }
 @Test(priority=3)
 public void testTimesheet() throws InterruptedException
 {   	 float totaltime = obj.getTotalWeekTime(false);
	 assertEquals(obj.convertStringToInteger((obj.getWebElement(obj.sreader.getObjByElement("totaltime")).getText()),false), totaltime,.02);
     float extraTotalTime = obj.getTotalWeekTime(true);
     System.out.println("extra time is   :->"+extraTotalTime+"   total time + extra time :->"+(extraTotalTime+totaltime));
    
 }
 @Test(priority=4)
 public void testLeave() throws InterruptedException
 {  
	 int date = obj.getTokenTime("date");
	 System.out.println("date"+date);
   int month = obj.getTokenTime("month");
   Actions action = new Actions(driver);
   for(int i= date;i<date+5;i++)
   {
	   action.moveToElement(obj.getWebElement(obj.sreader.getObjByElement("timebox"), i+"")).perform();
	  	 assertEquals(obj.getWebElement(obj.sreader.getObjByElement("leave"), i+"", "BOOTO").getText(), "BOOTO");
	  	 assertEquals(obj.getWebElement(obj.sreader.getObjByElement("leave"), i+"", "FSL").getText(), "FSL");
	  	 assertEquals(obj.getWebElement(obj.sreader.getObjByElement("leave"), i+"", "HSL").getText(), "HSL");   
   }
 }
 @AfterTest
 public void quitDriver()
 {
	 driver.quit();
 }
 
 
 
}
