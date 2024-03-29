package Utility;

import java.awt.AWTException;
import java.awt.Rectangle; 
import java.awt.Toolkit; 
import java.awt.Robot; 
import java.awt.image.BufferedImage; 
import java.io.IOException;
import java.io.File; 
import javax.imageio.ImageIO;
import Utility.CommonFunctions;
import Utility.ExtentRepotEx;

public class ImagecaptureConfig extends ExtentRepotEx
{

	 Robot robot =null;
	 BufferedImage screenFullImage=null;
	 File output=null;
	 
	 public static String format = "jpg";
	 public static String imagepath = "D:\\Automation\\Projects\\Workspace\\WiproTest\\Reports\\Report_"+ExtentRepotEx.timestamp+"\\Screenshot"+ExtentRepotEx.timestamp+"." + format;
	/*public static  Random rand = new Random(); 
	 static public int randomnum = rand.nextInt(1000);*/ 
	  

	  public void capturesnapshot_Fail()
	  {
		  try 
		  { 
	            Thread.sleep(120); 
	            Robot robot1 = new Robot();
	        	            
	           // for full image capture
	            Rectangle screenRect1 = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	            BufferedImage screenFullImage1 = robot1.createScreenCapture(screenRect1);
	            ImageIO.write(screenFullImage1, format, new File(imagepath));
	            Thread.sleep(1000);
	            //for partial image capture
	            /*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            Rectangle captureRect = new Rectangle(0, 0, screenSize.width / 2, screenSize.height / 2);
	            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
	            ImageIO.write(screenFullImage, format, new File(fileName));*/
	       } 
	       catch (AWTException | IOException | InterruptedException ex) 
		   { 
	            System.out.println(ex); 
	       } 
	  }
	  public void capturesnapshot()
	  {
		try 
		{
            Thread.sleep(120);
			robot=new Robot();
           
           // for full image capture
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

			screenFullImage = robot.createScreenCapture(screenRect);
			output=new File("D:\\Automation\\Projects\\Workspace\\WiproTest\\Reports\\Report_"+ExtentRepotEx.timestamp+"\\Screenshot_"+System.currentTimeMillis()+ "." + "jpg");
            ImageIO.write(screenFullImage, "jpg",output);
            Thread.sleep(2000);
    		
            //for partial image capture
            /*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle captureRect = new Rectangle(0, 0, screenSize.width / 2, screenSize.height / 2);
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
            ImageIO.write(screenFullImage, format, new File(fileName));*/
        } 
        catch (Exception e) 
		{ 
           e.printStackTrace();
        } 
	  }
      public void reportsnapshotPass(String detail)
      {
          try
          {
        	   CommonFunctions cf=new CommonFunctions();
        	   cf.pass(detail+ test.addScreenCapture(output.getAbsolutePath().replace("D:\\Automation\\Projects\\Workspace\\WiproTest\\Reports\\Report_"+ExtentRepotEx.timestamp+"\\","")));
        	   Thread.sleep(2000);
        	   
          }catch(Exception e)
          {
        		   e.printStackTrace();
          }
       }
	
       public void reportsnapshotFail(String detail)
       {
          try
          {
        	   CommonFunctions cf=new CommonFunctions();
        	   //cf.pass(detail+ test.addScreenCapture("./Screenshot_"));
        	 //  cf.fail(detail+ test.addScreenCapture("./Screenshot"+ExtentRepotEx.timestamp+"." +"jpg"));
        	   cf.fail(detail+ test.addScreenCapture(output.getAbsolutePath().replace("D:\\Automation\\Projects\\Workspace\\WiproTest\\Reports\\Report_"+ExtentRepotEx.timestamp+"\\","")));
        	   Thread.sleep(1000);
       	  }
          catch(Exception e)
          {
        		   e.printStackTrace();
          }
       }
	
}
