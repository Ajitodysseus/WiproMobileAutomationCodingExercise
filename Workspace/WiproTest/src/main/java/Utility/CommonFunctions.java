package Utility;

import java.util.Properties;
import com.relevantcodes.extentreports.LogStatus;
import Utility.ExtentRepotEx;

public class CommonFunctions extends ExtentRepotEx 
{

	 public static Properties properties;
	 public  static String workingDir = System.getProperty("user.dir");
	// public  String propertyFilePath=workingDir+"\\src\\data\\findpatient.properties";
	

	
	public  void Info(String detail)
	{
		test.log(LogStatus.INFO, detail);
	}
	public  void pass(String detail)
	{
		test.log(LogStatus.PASS, detail);
	}
	public  void fail(String detail)
	{
		test.log(LogStatus.FAIL, detail);
	}
	public  void skip(String detail)
	{
		test.log(LogStatus.SKIP, detail);
	}
	public void TestDataReader() 
	{
		// TODO Auto-generated method stub
		
	}
	public void pass(String details, boolean b) 
	{
		// TODO Auto-generated method stub
		test.log(LogStatus.PASS, details);
	}
	public void fail(String details, boolean b)
    {
		// TODO Auto-generated method stub
		test.log(LogStatus.FAIL, details);
	}

}
