package Implementation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserLogin 
{

	
	@FindBy(xpath="//*[contains(text(), 'Already a customer? Sign In')]")
	private WebElement Createacc;
	
	@FindBy(xpath="//*[contains(text(), 'Mobile number or Email')]")
	private WebElement MobileNo;
	
	@FindBy(xpath="//*[contains(text(), 'Continue')]")
	private WebElement cntinue;
	
	@FindBy(xpath="//*[contains(text(), 'Amazon password')]")
	private WebElement Pwd;
	
	@FindBy(xpath="//*[contains(text(), 'Login')]")
	private WebElement lgn;
	
	
	
	public void SignIn(String Mbl, String paswrd)
	{
		Createacc.click();
		MobileNo.sendKeys(Mbl);
		cntinue.click();
		Pwd.sendKeys(paswrd);
		lgn.click();
	}
	
	
	
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
