package Objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UMRObjects {
	WebDriver driver;
	Utility utility= new Utility();

	@FindBy(id="toggleLoginOrRegister")
	public WebElement LoginButton;
	
	@FindBy(id="username")
	public WebElement UserNameField;
	
	@FindBy(id="password")
	public WebElement PasswordField;
	
	@FindBy(id="goButton")
	public WebElement GoButton;
	
	@FindBy(id="answer1")
	public WebElement Answer1Field;
	
	@FindBy(id="answer2")
	public WebElement Answer2Field;
	
	@FindBy(id="validateQuestionsButton")
	public WebElement SubmitButton;
	
	@FindBy(id="memberId")
	public WebElement SSNField;
	
	@FindBy(id="expandButton")
	public WebElement ExpandButton;
	
	@FindBy(xpath="//label[text()='Claims']/input[@value='ClaimsPrvd']")
	public WebElement ClaimsOption;
	
	@FindBy(id="navSearchButton")
	public WebElement SearchButton;
	
	@FindBy(id="claimsTimeperiodDateRange")
	public WebElement DateRangeCheckbox;
	
	@FindBy(id="fromDateforClaims")
	public WebElement FromDateField;
	
	@FindBy(id="toDateforClaims")
	public WebElement ToDateField;
	
	@FindBy(id="mainSearchNavMember")
	public WebElement SearchButton1;
	
	@FindBy(xpath="//span[@id='eobClaimViewAnchor']//a")
	public List<WebElement> ViewEOBButton;
	
	@FindBy(xpath="//span[text()='Print']")
	public WebElement PrintSpan;
	
	@FindBy(xpath="//a[@id='viewLink']")
	public List<WebElement> ViewLinks;
	
	public UMRObjects(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}
	public WebElement ReturnSearchedPatient(String name) {

		WebElement patient;
		try {
			utility.Pause(driver, driver.findElement(By.id(name)),"Visibility", 30);
				}catch(Exception ex1) {}
		try {
		patient=driver.findElement(By.id(name));   
		}catch(Exception ex1) {
			patient=null;
		}
		return patient;
	} 
	
}
