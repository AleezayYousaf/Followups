package Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import Objects.Utility;



public class FramesObjectClass {

   
	Utility utility=new Utility();
	public static ArrayList PatientList = new ArrayList();
	Logger logger = Logger.getLogger("FramesObjectClass");
	WebDriver driver;
   
	@FindBy(id="fraContent")
    WebElement ContentFrame;
	
	@FindBy(id="messageFrame")
    WebElement messageFrame;

	@FindBy(id="fAmbTran")
    public WebElement ambTransportFrame;
    
	@FindBy(id="ctl00_CP1_UcAddEditAppointments1_ifrmPatHotList")
    public WebElement hotListFrame;

	@FindBy(id="fraCureMD_Body")
    WebElement mainBodyFrame;
	
	@FindBy(id="livespell_css_selected_id_undefined")
    WebElement livespellFrame;
	
	@FindBy(id="frmTaskDetail")
    WebElement frmTaskDetailFrame;
	
	@FindBy(id="Iframe1")
    WebElement iFrame1;

	@FindBy(id="Rpt")
    WebElement Rpt;
	
	@FindBy(id="ifrmBookSearchedAppt")
    public WebElement iFromBookSearchAppt;

	@FindBy(id="fraCureMD_Patient_Menu")
    WebElement PatientMenuCureMD;

	@FindBy(id="DynamicBHdialogbox")
    WebElement DynamicBHDialogBox;

	@FindBy(id="fraLog")
    WebElement FraLog;

	@FindBy(id="iFrameLst")
    WebElement IFrameList;

	@FindBy(id="DynamicDialogBox")
    WebElement DynamicDialogBox;

	@FindBy(id="Explorer")
    WebElement EXPLORER;

	@FindBy(id="Thumbs")
    WebElement THUMBS;
	
	@FindBy(id="CustomFolders")
    WebElement customFolders;
	
	@FindBy(id="myFrame")
    WebElement MyFrame;
	
	@FindBy(id="fraCureMD_Menu")
    WebElement CureMDMenu;
	
	@FindBy(id="fraReviews")
    WebElement FraReviews;
	
	@FindBy(id="fraCodes")
    WebElement fraCodesFrame;
	
	@FindBy(id="lstFrame")
    WebElement LstFrame;
	
	@FindBy(id="frame12")
    WebElement Frame12;
	
	@FindBy(id="fraMessage")
    WebElement fraMessageFrame;
	
	@FindBy(id="srchFram")
    WebElement srchFramFrame;
	
	@FindBy(id="fraCureMD_Patient_Header")
    WebElement PatientHeaderFrame;
	
	@FindBy(id="pftFrame")
    WebElement PFTFrame;
	
	@FindBy(id="ifHidden")
	WebElement ifHiddenFrame;
	
	@FindBy(id="fraInsurance")
    WebElement FraInsurance;
	
	@FindBy(id="BillingtabsFrame")
    WebElement BillingTabsFrame;
	
	@FindBy(id="framePatientDemo")
    WebElement PatientDemoFrame;
	
	@FindBy(id="frameHeader")
    WebElement HeaderFrame;
	
	@FindBy(id="frame1")
    WebElement frame1Frame;
	
	@FindBy(id="ListFrame")
    WebElement ListFrame;
	
	@FindBy(id="frmImmunization")
    WebElement frmImmunizationFrame;
	
	@FindBy(id="ifrEligibility")
    WebElement ifrEligibilityFrame;
	
	@FindBy(id="frmRestrictedUser")
    WebElement frmRestrictedUserFrame;
	
	@FindBy(id="fraTab")
    WebElement FraTabFrame;
	

	@FindBy(id="fraOP")
	WebElement FraOP;
	
	@FindBy(id="refprovider")
	WebElement refProvider;
    
	@FindBy(id="DynamicSettingDiagnosis")
	WebElement DynamicSettingDiagnosis;
   
   	@FindBy(id="ChargesFiles")
    WebElement ChargesFiles;
   	
   	@FindBy(id="fraCureMD_Messenger")
    WebElement fraCureMD_Messenger;
	
	@FindBy(id="iFrameList")
    WebElement iFrameList;
	
	@FindBy(id="ToBeRev")
    WebElement ToBeRev;
	
	@FindBy(id="fraAttachment")
    WebElement fraAttachment;
	
	@FindBy(id="consentdocs")
    WebElement consentdocs;
	
	@FindBy(id="fraDocs")
    WebElement fraDocs;
	
	@FindBy(id="fraNDC_List")
    WebElement fraNDC_List;		
	
	@FindBy(xpath="//*[@id=\"xEditingArea\"]//iframe")
    WebElement  EditingAreaFrame;
	
	@FindBy(id="vNotes___Frame")
    WebElement  vNotesFrame;
	
	@FindBy(id="fraRefProvider_List")
    WebElement  fraRefProvider_List;	
	
	@FindBy(id="fSparcs")
    WebElement fSparcsFrame;
	
	@FindBy(id="genericPopUp")
    WebElement genericPopUpFrame;
	
	@FindBy(id="ifrmApptSearch")
	WebElement ApptSearchFrame;
	
	@FindBy(id="ReferralList")
    WebElement ReferralListFrame;
	
	@FindBy(id="fraRef_List")
    WebElement Ref_ListFrame;

	@FindBy(id = "ifrmCal")
	public WebElement iFrmCal;
	
	@FindBy(id="fraContent")
	public WebElement fraContent;
	
	@FindBy(id="frmSystemExam")
	public WebElement SystemExamFrame;
	
	@FindBy(id="ifrmQuestion")
	public WebElement QuestionFrame;
	
	@FindBy(id="DynamicSettingDiagnosis")
	public WebElement DynamicSettingDiagnosisFrame;
	
	@FindBy(id = "TabsFrame")
	public WebElement TabsFrame;
	
	@FindBy(id="frmclinicalsummary_ccm")
    WebElement ClinicalSummaryFrame;
	
	@FindBy(id="frmAlerts")
    WebElement AlertsFrame;

	@FindBy(id = "ctl00_CP1_ucAddEditApp1_ifrmPatHotList")
	public WebElement patHotListFrame;
	
	@FindBy(id="pdf-viewer")
    WebElement PDFViewerFrame;
	
	@FindBy(id="frameTabss")
    WebElement frameTabssFrame;
	
	@FindBy(id="frameTabs")
    WebElement frameTabsFrame;
    public FramesObjectClass(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);
        

    }

   
 



}