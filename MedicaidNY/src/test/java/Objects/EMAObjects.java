package Objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EMAObjects {
WebDriver driver;
	
	@FindBy(id="LoginPortletUsername")
	public WebElement UserNameField;
	
	@FindBy(id="LoginPortletPassword")
	public WebElement PasswordField;
	
	@FindBy(id="btnSignInSubmit")
	public WebElement LoginButton;
	
	@FindBy(id="layout-workflows-menu-button")
	public WebElement WorkflowsDD;
	
	@FindBy(xpath="//a[@id='mh-workflows-my-health-plans-menu' and contains(text(), 'My Health Plans')]")
	public WebElement MyHealthPlans;
	
	@FindBy(id="workflows-menu-plan-1199-nbf")
	public WebElement plan1199DEIU;

	@FindBy(name="Action_No")
	public WebElement NoButton;
	
	@FindBy(xpath="//a[contains(text(),'Claim Status Inquiry')]")
	public WebElement ClaimStatusInquiry;
	
	@FindBy(id="QueryModel_Provider")
	public WebElement BillingEntityField;

	@FindBy(id="QueryModel_MemberIdLocal")
	public WebElement MemberIdField;
	
	@FindBy(xpath="//a[@class='detail-link']")
	public List<WebElement> SearchResults;
	
	@FindBy(id="QueryModel_LastNameLocal")
	public WebElement LastNameField;
	
	@FindBy(id="QueryModel_FirstNameLocal")
	public WebElement FirstNameField;
	
	@FindBy(id="QueryModel_DateOfBirthLocal")
	public WebElement DOBField;
	
	@FindBy(id="QueryModel_ServiceDateRange_ServiceStartLocal")
	public WebElement DOSStartField;
	
	@FindBy(id="QueryModel_ServiceDateRange_ServiceEndLocal")
	public WebElement DOSEndField;
	
	@FindBy(id="search-buttonLocal")
	public WebElement SearchButton;
	
	@FindBy(xpath="//*[contains(text(),'No record')]")
	public WebElement noRecordFoundText;
	
	@FindBy(xpath="//a[contains(text(),'Sign Out')]")
	public WebElement signOutBtn;
	
	@FindBy(id="claim-details-print-link")
	public WebElement PrintButton;
	
	
	public EMAObjects(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}
}
