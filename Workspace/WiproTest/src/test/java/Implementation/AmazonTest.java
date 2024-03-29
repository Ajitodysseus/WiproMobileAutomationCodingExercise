package Implementation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utility.CommonFunctions;
import Utility.ExtentRepotEx;
import Utility.ImagecaptureConfig;
import Utility.LoginUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AmazonTest extends ExtentRepotEx
{
	  static WebDriver wd;
	  public AndroidDriver driver;
	  public RemoteWebDriver driver2;
   
	  DesiredCapabilities capabilities=DesiredCapabilities.android();
	  
      int scrollTimeout = 500;
  	  int header = 98;
  	  int footer = 0;
  	  float dpr = 2;

  	  String Native = "NATIVE_APP";
  	  String Webview = "WEBVIEW_1";
  	  
  	  UserLogin acnt;
  	  SearchItem srch;
  	  CardDetails card;
	  
      ImagecaptureConfig img = new ImagecaptureConfig();
	  CommonFunctions cmn = new CommonFunctions();
	  SoftAssert soft=new SoftAssert();
    
	@BeforeTest
    public void baseClass() throws InterruptedException, IOException 
    {
	  //Location of the application
   	    File app = new File("C:\\Users\\Ajit Nakum\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\SZQ5GQ54", "Amazon_shopping[1].apk");
   	 
   	    capabilities.setCapability("device","Android");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
   	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "VIBE K5 Note");
   	    capabilities.setCapability("app", app.getAbsolutePath());

   	    capabilities.setCapability("platformName","Android");
        capabilities.setCapability("UDID", "CEEEYLQ4R4WSNNJR"); //cmd >> adb devices
	
   	    capabilities.setCapability(CapabilityType.VERSION,"6.0");
 	    capabilities.setCapability("orientation", "PORTRAIT");
			
   	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
   	    Thread.sleep(2500);
    	    
      //set the package name of the app
   	    capabilities.setCapability("app-package", "com.example.android.contactmanager-1");
   	  //set the Launcher activity name of the app
   	    capabilities.setCapability("app-activity", ".ContactManager");
   	    Thread.sleep(1500);
   	    
   	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
   	    Thread.sleep(35000);
 	     
   	    acnt = PageFactory.initElements(driver, UserLogin.class);
   	    srch = PageFactory.initElements(driver, SearchItem.class);
   	    card = PageFactory.initElements(driver, CardDetails.class);
   	    
     }
    
	@Test(priority = 1, dataProvider = "Authentication")
    public void UserLogin(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		   try
		   {  
			     img.capturesnapshot();
			     img.reportsnapshotPass("Application Launched successfully." );
			     
			    //Print message in extent report 
			     cmn.Info("Create Amazon Account");
			     
			    //Call User login Page object 
			     acnt.SignIn(Mobile_Number, Password);
			     
			       WebDriverWait wait = new WebDriverWait (driver, 5540);
		  		   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Shop by Category')]")));
			     
		  		   
		  		    	if(driver.getPageSource().contains("Shop by Category"))
		  		    	{
		  		    		 img.capturesnapshot();
		  				     img.reportsnapshotPass("User logged In and navigate on homepage." );
		  				     
		  				     System.out.println("User logged In and navigate on homepage.");
		  		    	}
   		         
 		   }  
		   catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAIL");
			   AssertJUnit.assertTrue("Unable to login.", acnt.isDisplayed());
			   throw(e);
		   }
    }
	
	@Test(priority = 2, dataProvider = "Authentication")
    public void PurchaseProduct(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		   try
		   {  
			     cmn.Info("Search Item");
			     srch.SearchItems(Item_Name);
			     
			    //Store TV in string 
			     String name = "TCL 163.96 cm (65 inches) AI 4K UHD";
			     
			    //Select TV  
			     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(2)).scrollIntoView(new UiSelector().textContains(\""+name+"\").instance(0))").click();
		 		
			    //Get price
			     String price = driver.findElement(By.xpath("//*[@id='priceblock_dealprice']")).getText();
			     
			    //Get Description
			     @SuppressWarnings("unused")
				 String dscrptn = driver.findElement(By.xpath("//*[@id='featurebullets_feature_div']")).getText();
			     
			     //Click on Add to Cart
			     driver.findElement(By.xpath("//*[contains(text(), 'Add to Cart')]")).click();
			     Thread.sleep(1500);
			     
			        if(driver.getPageSource().contains("Added to cart"))
	  		    	{
	  		    		 img.capturesnapshot();
	  				     img.reportsnapshotPass("Item Added to Cart" );
	  				     
	  				     System.out.println("Item Added to Cart");
	  		    	}
			        
			        //Click on Cart
			         driver.findElement(By.xpath("//*[@id='nav-cart-count']")).click();
			         
			         //Click on proceed to buy
			           driver.findElement(By.xpath("//*[contains(text(), 'Proceed to Buy')]")).click();
			           
			         //Click on Use current Address
			           driver.findElement(By.xpath("//*[contains(text(), 'Use this address')]")).click();
			           
			         //Click on Continue
			           driver.findElement(By.xpath("//*[contains(text(), 'Continue')]")).click(); 
			           
			         //Select Debit card
			           driver.findElement(By.xpath("//*[contains(text(), 'Add Debit/Credit/ATM Card')]")).click();
			           
			         //Store Item name on Checkout page
			 		   String Itemname = "TCL 163.96 cm (65 inches) AI 4K UHD";
			 		   
			  		      //Verify Item name
			  		    	if(name.equals(Itemname))
			  		    	{
			  		    		 img.capturesnapshot();
			  				     img.reportsnapshotPass("Item Name matched.." );
			  				     
			  				     System.out.println("Item Name matched..");
			  		    	}
			  		    	
			  		       //Store price on Checkout page
					 		   String pricess = driver.findElement(By.xpath("//*[@id='price']")).getText();
					 		   
					  		      //Verify Item name
					  		    	if(pricess.equals(price))
					  		    	{
					  		    		 img.capturesnapshot();
					  				     img.reportsnapshotPass("Item price matched.." );
					  				     
					  				     System.out.println("Item preic matched..");
					  		    	}
			           
			          //Use Card details Page object
			           card.paymnt(Card_Number);
		  		    	
   		               System.out.println("Product purchase successfully..");
 		   }  
		   catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAIL");
			   AssertJUnit.assertTrue("Unable to add Item to card", acnt.isDisplayed());
			   throw(e);
		   }
    }
	
	
	@DataProvider
    public String[][] Authentication() throws Exception
    {   
		
 	     String POS_LoginExcel=properties.getProperty("LoginExcel_DataPath");
 	     
 	     String[][] testObjArray = LoginUtils.getTableArray(POS_LoginExcel+"\\AmazonData.xlsx","Sheet1");
 	     return testObjArray;
 	     
    }
    
    @AfterClass
    public void closeBrowser() throws InterruptedException
    {
          //  driver.close();
              driver.quit();
    }
}
