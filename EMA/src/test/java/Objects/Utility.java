package Objects;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.net.URL;

public class Utility {


	static FramesObjectClass framesObj;
	public static int k = 0;
	public static int l = 0;
	public static String val6 = "";
	public static String val7 = "";
	public static String val8 = "";
	public static String val9 = "";
	String currentComponent = "";
	public static WebDriverWait wait;
	WebElement element;
	Logger logger = Logger.getLogger("Utility");

	// --------------------------------------------------------------------------------- //
	//                            Frame switching Functions                              //
	// --------------------------------------------------------------------------------- //

	// This function will switch to frame of specified date in schedular
	public static String switchtoSchedularPanelFrame (WebDriver driver) throws InterruptedException {

		switchtomainBodyframe(driver);
		Date today;
		String strDate;
		DateFormat dateFormat;
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		today = new Date();
		strDate = dateFormat.format(today);

		String id = "";

		try {
			id = retryingReturnValue(driver,
					By.xpath("//*[@id=\"upnlPanels\"]//span[contains(text(),\"" + strDate + "\")]"), "id");
		}
		catch (Exception e) {
			id = retryingReturnValue(driver,
					By.xpath("//*[contains(@id, 'lblDateHeading')]"), "id");
		}

		String parts[] = id.split("g");

		String frameid = "ifrmSchedule" + parts[1];
		System.out.println(frameid);

		WebElement frame = driver.findElement(By.id(frameid));
		Pause(driver, frame, "Frame", 30);

		return strDate;

	}


	// This function will switch to ifrmBookSearchedAppt frame from Iframe1 frame
	public static void switchtoifrmBookSearchedApptFromIframe1(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.iFrame1, "Frame", 30);
		Pause(driver, framesObj.iFromBookSearchAppt, "Frame", 30);

	}

	// This function will switch to ApptSearchFrame from ifrmBookSearchedAppt frame in Iframe1 frame
	public static void switchtoApptSearchFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.iFrame1, "Frame", 30);
		Pause(driver, framesObj.iFromBookSearchAppt, "Frame", 30);
		Pause(driver, framesObj.ApptSearchFrame, "Frame", 30);
	}

	// This function will switch to ApptSearchFrame from ifrmBookSearchedAppt frame in mainbody Frame
	public static void switchtoApptSearchFrameFromMainBodyFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.iFromBookSearchAppt, "Frame", 30);
		Pause(driver, framesObj.ApptSearchFrame, "Frame", 30);
	}

	public void SwitchTofSparcs(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.fSparcsFrame, "Frame", 30);

	}

	// This function will switch to ifrmBookSearchedAppt frame from Iframe1 frame
	public static void switchtoDynamicSettingFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicSettingDiagnosis, "Frame", 30);

	}

	// This function will switch to Billingtabs frame
	public void SwitchToBillingtabsFrame(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.BillingTabsFrame, "Frame", 30);

	}

	// This function will switch to FraContent frame
	public void SwitchToFraContentFrame (WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.fraContent, "Frame", 30);

	}

	public void SwitchToDynamicSettingDiagnosisFrame (WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicSettingDiagnosisFrame, "Frame", 30);

	}


	// This function will switch to Billingtabs frame
	public void SwitchTofraCodesFrame(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.fraCodesFrame, "Frame", 30);

	}

	// This function will switch to fraInsuranceFrame frame
	public void switchtofraInsuranceFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.FraInsurance, "Frame", 30);

	}

	// This function will switch to fraInsuranceFrame frame from patient demo
	public void switchtofraInsuranceFrameFromPatientDemo(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.PatientDemoFrame, "Frame", 30);
		Pause(driver, framesObj.FraInsurance, "Frame", 30);

	}

	// This function will switch to CustomFolders frame
	public void switchtoCustomFolders(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.customFolders, "Frame", 30);

	}

	// This function will switch to ContentFrame frame
	public void SwitchToContentFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.ContentFrame, "Frame", 30);

	}

	// This function will switch to Thumbs frame
	public void switchtoThumbs(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.THUMBS, "Frame", 30);

	}

	// This function will switch to fraLog frame
	public void switchtofraLog(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.FraLog, "Frame", 30);

	}

	// This function will switch to fraInsuranceFrame frame
	public void switchtofraCureMD_MessengerFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.fraCureMD_Messenger, "Frame", 30);

	}

	// This function will switch to Messenger Frame From ifrm Cal
	public void switchtofraCureMD_MessengerFrameFromIfrmCal (WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.iFrmCal, "Frame", 30);
		Pause(driver, framesObj.fraCureMD_Messenger, "Frame", 30);

	}

	// This function will switch to frameHeader frame
	public void switchtoframeHeader(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.HeaderFrame, "Frame", 30);

	}

	// This function will switch to frameHeader frame
	public void switchtoReferralList(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.ReferralListFrame, "Frame", 30);

	}

	// This function will switch to frameHeader frame
	public void switchtoReferralListfromBH(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.ReferralListFrame, "Frame", 30);

	}


	public void switchtoLstFramefromBH(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.DynamicDialogBox, "Frame", 30);
		Pause(driver, framesObj.LstFrame, "Frame", 30);

	}
	// This function will switch to frameHeader frame
	public void switchtoChargesFiles(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.ChargesFiles, "Frame", 30);

	}

	// This function will switch to fraAttachment frame
	public void switchtofraAttachmentFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.fraAttachment, "Frame", 30);

	}

	// This function will switch to consentdocs frame
	public void switchtoConsentDocsFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.consentdocs, "Frame", 30);

	}

	// This function will switch to fraCureMD_Patient_Menu frame
	public static void switchtofraCureMD_Patient_Menu(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.PatientMenuCureMD, "Frame", 30);

	}

	public static void switchtoQuestionFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.fraContent, "Frame", 30);
		Pause(driver, framesObj.QuestionFrame, "Frame", 30);

	}

	public static void switchtoQuestionFrameFromMainBody(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.QuestionFrame, "Frame", 30);

	}
	// This function will switch to ToBeRev frame
	public static void switchtoToBeRevFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.ToBeRev, "Frame", 30);

	}

	// This function will switch to Iframe1 frame
	public static void switchtoIframe1(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.iFrame1, "Frame", 30);

	}

	// This function will switch to frame1 frame
	public static void switchtoframe1(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.frame1Frame, "Frame", 30);

	}

	// This function will switch to List Frame
	public static void switchtoListFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.ListFrame, "Frame", 30);

	}

	// This function will switch to frmImmunization Frame
	public static void switchtofrmImmunizationFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.frmImmunizationFrame, "Frame", 30);

	}

	// This fucntion will switch to dynamic Setting Diagnosis frame
	public static void switchtoDynamicSettingDiagnosis(WebDriver driver) {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicSettingDiagnosis, "Frame", 30);

	}
	// This function will switch to ifrmBookSearchedAppt frame

	public static void switchtoifrmBookSearchedAppt(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.iFromBookSearchAppt, "Frame", 30);

	}

	// This function will switch to DynamicBHdialogbox frame
	public void switchtoDynamicBHdialogbox(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);

	}

	// This function will switch to lstFrame frame
	public void switchtolstFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		Pause(driver, framesObj.LstFrame, "Frame", 60);


	}

	// This function will switch to iFrameLst frame from DynamicDialogBox frame.
	public void switchtoiFrameLstFromDynamicDialogBox(WebDriver driver) throws InterruptedException {

		switchtoDynamicDialogBoxFromBH(driver);
		Pause(driver, framesObj.IFrameList, "Frame", 30);
	}

	// This function will switch to iFrameLst frame.
	public void switchtoiFrameLst(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.IFrameList, "Frame", 30);

	}

	public void switchtofraMessageFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		//Pause(driver, framesObj.srchFramFrame, "Frame", 30);
		Pause(driver, framesObj.fraMessageFrame, "Frame", 30);
	}

	public void switchtosrchFramFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.srchFramFrame, "Frame", 30);
	}


	// This function will switch to iFrameList frame       
	public static void switchtoiFrameListFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.PatientMenuCureMD, "Frame", 30);
		Pause(driver, framesObj.iFrameList, "Frame", 30);

	}


	public void switchtofraReviews(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.FraReviews, "Frame", 30);

	}

	public void switchtoTHUMBS(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.THUMBS, "Frame", 30);

	}

	public void switchtoiFrameLstFromPatientMenu(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.PatientMenuCureMD, "Frame", 30);
		Pause(driver, framesObj.IFrameList, "Frame", 30);

	}

	// This function will switch to DynamicDialogBox frame.
	public void switchtoDynamicDialogBox(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.DynamicDialogBox, "Frame", 30);

	}

	public void switchtolivespellFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.livespellFrame, "Frame", 30);

	}

	// This function will switch to DynamicDialogBox from Body frame.
	public void switchtoDynamicDialogBoxFromBody(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.DynamicDialogBox, "Frame", 30);

	}

	// This function will switch to DynamicDialogBox from DynamicBHDialogBox frame.
	public void switchtoDynamicDialogBoxFromBH(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.DynamicDialogBox, "Frame", 30);

	}

	public void switchtoFrame12FromBH(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.DynamicDialogBox, "Frame", 30);
		Pause(driver, framesObj.Frame12, "Frame", 30);
	}

	public void switchtoFraOP(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.FraOP, "Frame", 30);

	}

	public void switchtoRefProviderFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.refProvider, "Frame", 30);

	}

	public void SwitchToRptDynamicBHDialogBox(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.MyFrame, "Frame", 30);
		Pause(driver, framesObj.Rpt, "Frame", 30);

	}

	public void switchtoBookSearchedAppt(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.iFromBookSearchAppt, "Frame", 30);

	}

	// This function will switch to Rpt Frame for Report Testing.
	public void SwitchToRpt(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.MyFrame, "Frame", 30);
		Pause(driver, framesObj.Rpt, "Frame", 30);

	}

	// This function will switch to Rpt Frame for Report Testing.
	public void SwitchToRptFromBH(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.MyFrame, "Frame", 30);
		Pause(driver, framesObj.Rpt, "Frame", 30);

	}

	// This function will switch to Rpt Frame for Letter Report Testing.
	public void SwitchToRptWithoutMainbody(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.MyFrame, "Frame", 30);
		Pause(driver, framesObj.Rpt, "Frame", 30);

	}

	// This function will switch to ClinicalSummaryFrame frame
	public void SwitchToClinicalSummaryFrame (WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.ClinicalSummaryFrame, "Frame", 30);

	}

	public void SwitchToAlertsFrame (WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.ClinicalSummaryFrame, "Frame", 30);
		Pause(driver, framesObj.AlertsFrame, "Frame", 30);

	}

	// This function will switch to PatientHeader frame
	public void switchtoPatientHeader(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.PatientHeaderFrame, "Frame", 30);

	}

	// This function will switch to PFTFrame frame according to specified parent

	public void switchtoCureMDMenu(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.CureMDMenu, "Frame", 30);

	}

	// Parameter parent specifies whether pftFrame is loaded in BH Dialog box or
	// mainBody
	public void switchtoPFTFrame(WebDriver driver, String parent) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		if (parent.contains("BH")) {
			Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
			Pause(driver, framesObj.PFTFrame, "Frame", 30);
		} else if (parent.contains("mainbody")) {
			Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
			Pause(driver, framesObj.PFTFrame, "Frame", 30);
		} else {

		}
	}

	public void SwitchToifHiddenFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.ifHiddenFrame, "Frame", 30);

	}

	public void SwitchToPatientDemoFrame(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.PatientDemoFrame, "Frame", 30);

	}

	public void SwitchTofrmRestrictedUserFrame(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.PatientDemoFrame, "Frame", 30);
		Pause(driver, framesObj.frmRestrictedUserFrame, "Frame", 30);

	}

	public void SwitchTofrmRestrictedUserFrameFromBodyFrame(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.frmRestrictedUserFrame, "Frame", 30);

	}

	public void SwitchToHeaderFrame(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.HeaderFrame, "Frame", 30);

	}
	public void SwitchToSystemExamFrame(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.SystemExamFrame, "Frame", 30);

	}

	public void SwitchToSystemExamFrameBH(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.SystemExamFrame, "Frame", 30);

	}

	public void SwitchToframe1(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.frame1Frame, "Frame", 30);

	}

	public void SwitchToifrEligibilityFrameFromBH(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.ifrEligibilityFrame, "Frame", 30);

	}

	public void SwitchToRef_ListFrameFromBH(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.Ref_ListFrame, "Frame", 30);

	}

	public void SwitchTofrmTaskDetailFrameFromBH(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.frmTaskDetailFrame, "Frame", 30);

	}

	public void SwitchToifrEligibilityFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.ifrEligibilityFrame, "Frame", 30);

	}

	public void SwitchToListFrameFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.ListFrame, "Frame", 30);

	}

	public void SwitchTorefproviderFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.refProvider, "Frame", 30);

	}


	public void SwitchToAmbTransportFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.ambTransportFrame, "Frame", 30);

	}

	// This function will switch to fraDocs frame.
	public void SwitchTogenericPopUpFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.genericPopUpFrame, "Frame", 30);

	}

	// This function will switch to fraDocs frame.
	public void SwitchTogenericPopUpFramefromMainBody(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.genericPopUpFrame, "Frame", 30);

	}

	public void switchtoctl00_CP1_UcAddEditAppointments1_ifrmPatHotList(WebDriver driver) throws InterruptedException {

		framesObj=new FramesObjectClass(driver);
		Pause(driver,framesObj.hotListFrame,"Frame",30);
	}

	// This function will switch to main menu frame.
	public void switchtomainmenuframe(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.CureMDMenu, "Frame", 30);
	}

	// This function will switch to fraDocs frame.
	public void switchtofraDocsframe(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.ifHiddenFrame, "Frame", 30);
		Pause(driver, framesObj.fraDocs, "Frame", 30);

	}
	// This function will switch to fraDocs frame.
	public void switchtofraDocsframefromBH(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.fraDocs, "Frame", 30);

	}


	// This function will switch to fraDocs frame.
	public void switchtofraDocsThroughMainBodyframe(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.fraDocs, "Frame", 30);

	}


	public static void switchtoCalFrameFromMainBodyframe(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.iFrmCal, "Frame", 30);
	}

	// This function will switch to Main Body Frame

	public static void switchtomainBodyframe(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
	}

	// This function will switch to myFrame Frame
	public void SwitchTomyFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.MyFrame, "Frame", 30);

	}

	// This function will switch to fraNDC_List frame.
	public void switchtofraNDC_Listframe(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.fraNDC_List, "Frame", 30);

	}

	// This function will switch to Explorer frame
	public static void switchtoExplorerFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.EXPLORER, "Frame", 30);

	}

	// This function will switch to fraRefProvider_List frame
	public void switchtofraRefProvider_List(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.fraRefProvider_List, "Frame", 30);

	}

	// This function will switch to EditingAreaFrame frame
	public void switchtoEditingAreaFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.vNotesFrame, "Frame", 30);
		Pause(driver, framesObj.EditingAreaFrame, "Frame", 30);

	}
	// This function will switch to Fra Tab Frame

	public void switchtoFraTabframe(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.FraTabFrame, "Frame", 30);
	}

	// This function will switch to Fra Tab Frame from TabsFrame
	public void switchtoFraTabframeFromTabsFrame(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.mainBodyFrame, "Frame", 30);
		Pause(driver, framesObj.TabsFrame, "Frame", 30);
		Pause(driver, framesObj.FraTabFrame, "Frame", 30);
	}

	// This function will switch to myFrame Frame
	public void SwitchTomyFrameFromBH(WebDriver driver) throws InterruptedException {

		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.DynamicBHDialogBox, "Frame", 30);
		Pause(driver, framesObj.MyFrame, "Frame", 30);

	}

	// This function will switch to PDFViewer Frame
	public void SwitchTPDFViewerFrame(WebDriver driver) throws InterruptedException {
		framesObj = new FramesObjectClass(driver);
		driver.switchTo().defaultContent();
		Pause(driver, framesObj.PDFViewerFrame, "Frame", 30);
	}
	
	// This function will handle window change
	public void switchtowindowhandler(WebDriver driver) {

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			System.out.println(driver.getTitle());
		}

	}

	// ------------------------- End of Frame Switching Functions ---------------------- //



	// --------------------------------------------------------------------------------- //
	//                            POM Functions to access Elements                       //
	// --------------------------------------------------------------------------------- //

	// This function will click Main Menu Button according to its 'alt' attribute

	public void clickMenuButton(WebDriver driver, String value) throws InterruptedException {
		switchtomainmenuframe(driver);
		scrollTo(driver, driver.findElement(By.xpath("//*[@id=\"imgTD\"]//img[contains(@alt,'" + value + "')]")));
		retryingFindClick1(driver, By.xpath("//*[@id=\"imgTD\"]//img[contains(@alt,'" + value + "')]"));

	}

	public void checkPageIsReady(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");

		}
		// This loop will rotate for 25 times to check If page Is ready after every 1
		// second.
		// You can replace your value with 25 If you wants to Increase or decrease wait
		// time.
		else {

			for (int i = 0; i < 25; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				// To check page ready state.
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					System.out.println("Page Is loaded.");
					break;
				}
			}
		}

	}

	// This function will wait for element according to time(seconds) and condition
	// (Visibility,Click and Frame)
	public static void Pause(WebDriver driver, WebElement element, String KeyWord, int timeInSeconds) {

		wait = new WebDriverWait(driver, timeInSeconds); // time to pause in seconds
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {

			try {

				if (KeyWord.contains("Visibility")) {
					wait.until(ExpectedConditions.visibilityOf(element)); // waiting for the element to be visible

				} else if (KeyWord.contains("Click")) {
					wait.until(ExpectedConditions.elementToBeClickable(element)); // waiting for the element to be
					// Clickable
				} else if (KeyWord.contains("Frame")) {
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));// waiting for the frame to
					// be available and switch
					// to it
				} else if (KeyWord.contains("Invisibility")) {
					wait.until(ExpectedConditions.invisibilityOf(element));
				} else if (KeyWord.contains("Alert")) {
					wait.until(ExpectedConditions.alertIsPresent());
				}

				result = true;
				break;
			} catch (StaleElementReferenceException e) {

			}
			attempts++;
		}

	}

	public static void PomFindClick(WebDriver driver, WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				Utility.scrollTo(driver, element);
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static void PomFindClick1(WebDriver driver, WebElement element) throws InterruptedException {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {

				Utility.scrollTo(driver, element);
				Thread.sleep(1500);
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static void PomFindMove(WebDriver driver, WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {

				Utility.scrollTo(driver, element);
				movetoHover(driver, element);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static void PomFindScroll(WebDriver driver, WebElement element) {
		boolean result = false;

		int attempts = 0;

		while (attempts < 4) {

			try {
				Utility.scrollTo(driver, element);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}


	public static void PomFindClear(WebDriver driver, WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {

				Utility.scrollTo(driver, element);
				moveto(driver, element);
				element.clear();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}


	public static void PomFindHover(WebDriver driver, WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {

				Utility.scrollTo(driver, element);
				movetoHover(driver, element);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}


	public static void PomFindField(WebDriver driver, WebElement element, String text) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {

				Utility.scrollTo(driver, element);

				element.clear();
				element.sendKeys(text);

				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}


	public static void PomFindField(WebDriver driver, WebElement element, Keys key) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {

				Utility.scrollTo(driver, element);
				element.sendKeys(key);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}


	public static String PomReturnText(WebDriver driver, WebElement element) {
		boolean result = false;
		String val1 = "";
		int attempts = 0;
		while (attempts < 2) {
			try {

				Utility.scrollTo(driver, element);
				val1 = element.getText();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return val1;
	}


	public static String PomReturnValue(WebDriver driver, WebElement element, String Attribute) {
		boolean result = false;
		String val7 = "";
		int attempts = 0;
		while (attempts < 2) {
			try {
				val7 = element.getAttribute(Attribute);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return val7;
	}


	public static void PomReturnSelect(WebDriver driver, WebElement element, String VisibleText) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {

				scrollTo(driver, element);
				Select pcode = new Select(element);
				pcode.selectByVisibleText(VisibleText);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}


	public static void PomReturnSelect(WebDriver driver, WebElement element, int visibleIndex) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {

				scrollTo(driver, element);
				Select pcode = new Select(element);
				pcode.selectByIndex(visibleIndex);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static String PomReturnCssValue(WebDriver driver, WebElement element, String CssValue) {
		boolean result = false;
		String val7 = "";
		int attempts = 0;
		while (attempts < 2) {
			try {
				val7 = element.getCssValue(CssValue);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return val7;
	}

	//Returns Selected value of Dropdown
	public String getDropdownFirstSelectedOption(WebDriver driver, WebElement element) {

		Select select = new Select(element);
		String firstSelectedOption = select.getFirstSelectedOption().getText();

		return firstSelectedOption;
	}

	// Returns All Dropdown Values 
	public List<WebElement> getDropdownValues(WebDriver driver, WebElement element) {

		Select select = new Select(element);
		List<WebElement> options = select.getOptions();

		return options;
	}

	// ------------------------- End of POM Functions ---------------------- //



	// --------------------------------------------------------------------------------- //
	//                            Older Functions to access Elements                     //
	// --------------------------------------------------------------------------------- //

	public void CallToJSFunction(WebDriver driver, String jsfunction) {
		((JavascriptExecutor) driver).executeScript("javascript:" + jsfunction + "");
	}

	// All the functions with retryingFind(Locator) & PomFind(WebElement) will
	// perform certain actions on specified element and retryingReturn(Locator) &
	// PomReturn(WebElement) will return Text or attribute value.
	public static void retryingFindClick(WebDriver driver, By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				moveto(driver, element);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}



	public static void retryingFindClick1(WebDriver driver, By by) throws InterruptedException {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				Thread.sleep(1500);
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}


	public static void retryingFindMove(WebDriver driver, By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				movetoHover(driver, element);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}



	public static void retryingFindScroll(WebDriver driver, By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}


	public static void retryingFindClear(WebDriver driver, By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				moveto(driver, element);
				element.clear();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}
	public static void retryingFindHover(WebDriver driver, By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				movetoHover(driver, element);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static void retryingFindField(WebDriver driver, By by, String text) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				element.clear();
				element.sendKeys(text);

				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static void retryingFindFieldwithoutClear(WebDriver driver, By by, String text) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				element.sendKeys(text);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static void retryingFindField(WebDriver driver, By by, Keys key) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				element.sendKeys(key);

				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static String retryingReturnText(WebDriver driver, By by) {
		boolean result = false;
		String val1 = "";
		int attempts = 0;
		while (attempts < 2) {
			try {
				WebElement element = driver.findElement(by);
				Utility.scrollTo(driver, element);
				val1 = driver.findElement(by).getText();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return val1;
	}

	public static String retryingReturnValue(WebDriver driver, By by, String Attribute) {
		boolean result = false;
		String val7 = "";
		int attempts = 0;
		while (attempts < 2) {
			try {
				val7 = driver.findElement(by).getAttribute(Attribute);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return val7;
	}

	public static void retryingReturnSelect(WebDriver driver, By by, String VisibleText) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				WebElement element = driver.findElement(by);
				scrollTo(driver, element);
				Select pcode = new Select(driver.findElement(by));
				pcode.selectByVisibleText(VisibleText);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static void retryingReturnSelect(WebDriver driver, By by, int visibleIndex) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				WebElement element = driver.findElement(by);
				scrollTo(driver, element);
				Select pcode = new Select(driver.findElement(by));
				pcode.selectByIndex(visibleIndex);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}

	}

	public static String retryingReturnCssValue(WebDriver driver, By by, String CssValue) {
		boolean result = false;
		String val7 = "";
		int attempts = 0;
		while (attempts < 2) {
			try {
				val7 = driver.findElement(by).getCssValue(CssValue);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return val7;
	}

	// This function will return Tool tip Text of specified element
	public String ReturnTooltipText(WebDriver driver, WebElement elem) throws InterruptedException {
		Actions ToolTip1 = new Actions(driver);
		Thread.sleep(2000);
		ToolTip1.clickAndHold(elem).perform();
		String ToolTipText = elem.getAttribute("title");
		return ToolTipText;
	}

	// This function will scroll the element
	public static void scrollTo(WebDriver driver, WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	// This function will move webdriver to the specified element.
	// This function move driver to specified elemenet
	public static void moveto(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click().build().perform();

	}

	// This function will hover webdriver on to the specified element.
	public static void movetoHover(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();

	}


	// ------------------------- End of Older Functions ---------------------- //


	// --------------------------------------------------------------------------------- //
	//                            Date/Time Functions                                    //
	// --------------------------------------------------------------------------------- //

	// This function will format specified date according to format yyyy-MM-dd
		public String dateformatInDashFormat(String date) throws ParseException {


			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date1 = df.parse(date);
			//System.out.println(new SimpleDateFormat("MM-dd-yyyy").format(date1));
			String finalDate = new SimpleDateFormat("MM/dd/yyyy").format(date1);
			return finalDate;

		}
		
	// This function will format specified date according to format yyyy-MM-dd
	public String dateformat(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = df.parse(date);

		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");

		String finalString = newFormat.format(date1);

		String tim = finalString;

		return tim;

	}

	// This function will return specified time in 24 Hrs format
	public String toTwentyFour(String time) throws ParseException {
		// Format of the date defined in the input String
		DateFormat df = new SimpleDateFormat("hh:mm aa");
		// Desired format: 24 hour format: Change the pattern as per the need
		DateFormat outputformat = new SimpleDateFormat("HH:mm");
		Date date = null;
		String output = null;

		// Converting the input String to Date
		date = df.parse(time);
		// Changing the format of date and storing it in String
		output = outputformat.format(date);
		// Displaying the date
		return output;

	}

	// This function will return Current Time
	public String getCurrentTime() throws ParseException {

		Calendar cal = Calendar.getInstance();
		DateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		String time1 = sdf.format(cal.getTime());

		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = parseFormat.parse(time1);
		String time = displayFormat.format(date);

		return time1;

	}

	// This function will return Current Time
	public String getCurrentTimeHrsMins() throws ParseException {

		Calendar cal = Calendar.getInstance();
		DateFormat sdf = new SimpleDateFormat("HH:mm a");

		String time1 = sdf.format(cal.getTime());

		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm a");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		Date date = parseFormat.parse(time1);
		String time = displayFormat.format(date);

		return time1;

	}

	// This function will return Current Time (Duplicate)
	// Displaying current time in 12 hour format with AM/PM
	public String CurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("h:mm");
		String dateString = dateFormat.format(new Date()).toString();
		return dateString;
	}

	// This function will return Current Hour
	public String CurrentHour() {
		// Displaying current time in 12 hour format with AM/PM
		DateFormat dateFormat = new SimpleDateFormat("hh");
		String dateString = dateFormat.format(new Date()).toString();
		// System.out.println("Current time in AM/PM: "+dateString);
		return dateString;
	}


	// This function will return the date by adding specified number of days to
	// todays date.
	// This function is returning date after adding specified number of days to
	// current date
	public String AddDate(int number) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, number);

		Date currentDatePlusOne = c.getTime();
		String setDate = dateFormat.format(currentDatePlusOne).toString();
		return setDate;

	}

	// This function will return the date by adding specified number of days to
	// specified date.
	// This function is returning date after adding specified number of days to
	// specified date
	public String AddDateSpecific(int number, int year, int month, int date) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, date);
		c.add(Calendar.DATE, number);

		Date currentDatePlusOne = c.getTime();
		String setDate = dateFormat.format(currentDatePlusOne).toString();
		return setDate;

	}

	// This function will return the date by adding specified number of days to
	// specified date.
	// This function is returning date of first Monday of the month of specified
	// year & month
	// month input starts with zero index

	public static String getFirstMonday(int year, int month) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cacheCalendar = Calendar.getInstance();
		cacheCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cacheCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		cacheCalendar.set(Calendar.MONTH, month);
		cacheCalendar.set(Calendar.YEAR, year);
		Date currentDate = cacheCalendar.getTime();
		String CurrentDate = dateFormat.format(currentDate).toString();
		return CurrentDate;

	}

	// This function will return the date of first monday of the next month of the
	// specified date.
	// This function is returning date of next Monday from specified year,month &
	// date
	// month input starts with zero index
	public String getNextMonday(int year, int month, int date) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar date1 = Calendar.getInstance();
		date1.set(year, month - 1, date);

		while (date1.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			date1.add(Calendar.DATE, 1);
		}

		Date currentDate = date1.getTime();
		String CurrentDate = dateFormat.format(currentDate).toString();
		return CurrentDate;
	}

	// This function will return the number of days to next sunday from the
	// specified date.
	// This function is returning date of next Monday from specified year,month &
	// date
	// month input starts with zero index
	public int getDaystoSunday(int year, int month, int date) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar date1 = Calendar.getInstance();
		date1.set(year, month - 1, date);
		int i = 1;
		while (date1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			date1.add(Calendar.DATE, 1);
			i++;
		}

		return i;
	}

	// This function will return the Todays Date.
	// This function is returning todays date
	public static String TodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar date1 = Calendar.getInstance();
		Date currentDate = date1.getTime();
		String CurrentDate = dateFormat.format(currentDate).toString();
		return CurrentDate;

	}

	// This function is returning Current month
	public static String CurrentMonth() {
			DateFormat dateFormat = new SimpleDateFormat("MMMM");
			Calendar date1 = Calendar.getInstance();
			Date currentDate = date1.getTime();
			String CurrentDate = dateFormat.format(currentDate).toString();
			return CurrentDate;

		}
	
	// This function is returning Current year
	public static String CurrentYear() {
			DateFormat dateFormat = new SimpleDateFormat("YYYY");
			Calendar date1 = Calendar.getInstance();
			Date currentDate = date1.getTime();
			String CurrentDate = dateFormat.format(currentDate).toString();
			return CurrentDate;

		}
	
	// This function will return true if Today is Saturday or Sunday or else it will
	// return false.
	public boolean DayOfWeek(int number) {
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, number);
		boolean flag = false;
		if ((c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
			flag = true;
		}

		return flag;

	}

	// This function will return the present month of the present year.
	// This function is returning name of the month of specified number of month
	String getMonthForInt(int num) {
		String month = "";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;

	}

	// This function is returning previous month
	public static String PreviousMonth() {
			DateFormat dateFormat = new SimpleDateFormat("MMMM");
			Calendar date1 = Calendar.getInstance();
			date1.add(Calendar.MONTH, -1);
			Date currentDate = date1.getTime();
			String CurrentDate = dateFormat.format(currentDate).toString();
			return CurrentDate;

		}
	
	// This function will format specified date according to format yyyy-MM-dd
			public String dateformatForCureBilling(String date) throws ParseException {
				
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date date1 = df.parse(date);
				//System.out.println(new SimpleDateFormat("MM-dd-yyyy").format(date1));
				String finalDate = new SimpleDateFormat("MM-dd-yyyy").format(date1);
				return finalDate;

			} 
			
			public String dateformatForCureBillingWithoutZero(String date) throws ParseException {


				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date date1 = df.parse(date);
				//System.out.println(new SimpleDateFormat("MM-dd-yyyy").format(date1));
				String finalDate = new SimpleDateFormat("M/d/yyyy").format(date1);
				return finalDate;

			} 
	// --------------------------- End of Date/Time Functions -------------------------- //


	// --------------------------------------------------------------------------------- //
	//                            Tab Switching Functions                                //
	// --------------------------------------------------------------------------------- //

	// This function will switch between tabs according to give tab name in main
	// body frame
	// This Done
	// it will be used to switch between tabs(like in Lab order we want to switch
	// between pending order, Received result, Acknowledge, deleted tabs).
	public void switchBetweenTabs(WebDriver driver, String tabName) throws InterruptedException {
		try {
			switchtomainBodyframe(driver);
			try {
				retryingFindClick1(driver, By.xpath(
						"//*[@id=\"tblTabsPages\"]//span[contains(@class, 'mid') and text() = '" + tabName + "']"));
			} catch (Exception e) {
				retryingFindClick1(driver, By.xpath(
						"//*[@id=\"tblSubTabsPages\"]//span[contains(@class, 'mid') and text() = '" + tabName + "']"));
			}
		} catch (Exception excep) {
			switchtoDynamicBHdialogbox(driver);
			try {
				retryingFindClick1(driver, By.xpath(
						"//*[@id=\"tblTabsPages\"]//span[contains(@class, 'mid') and text() = '" + tabName + "']"));
			} catch (Exception e) {
				retryingFindClick1(driver, By.xpath(
						"//*[@id=\"tblSubTabsPages\"]//span[contains(@class, 'mid') and text() = '" + tabName + "']"));
			}
		}

	}

	public void switchBetweenTabsWithoutFrameSwitching(WebDriver driver, String tabName) throws InterruptedException {

		try {
			retryingFindClick1(driver,
					By.xpath("//*[@id=\"tblTabsPages\"]//span[contains(@class, 'mid') and contains(text(), '" + tabName
							+ "')]"));
		} catch (Exception e) {
			retryingFindClick1(driver,
					By.xpath("//*[@id=\"tblSubTabsPages\"]//span[contains(@class, 'mid') and contains(text(),'"
							+ tabName + "')]"));
		}

	}

	// This function will switch between tabs according to give tab name in
	// DynamicBHDialogBox frame
	// This Done
	// ** Changed the frames before checking for Tabs and also checked the ACTIVE
	// tab first due to multiple enteries of Tabs **//
	public void switchBetweenDynamicBHDialogBoxTabs(WebDriver driver, String tabName) throws InterruptedException {

		switchtoDynamicBHdialogbox(driver);

		List<WebElement> subTabParents = driver.findElements(By.xpath("//table[@id='tblSubTabsPages']/parent::*"));
		String temp = "";
		// Getting the current active tab id - from disease, precautions or
		// hospitalization
		for (int j = 0; j < subTabParents.size(); j++) {
			temp = PomReturnCssValue(driver, subTabParents.get(j), "display");
			if (temp.contains("block")) {
				temp = PomReturnValue(driver, subTabParents.get(j), "id");
				break;
			}
		}

		retryingFindClick1(driver, By.xpath(
				"//*[@id=\"" + temp + "\"]//span[contains(@class, 'mid') and contains(text() , '" + tabName + "')]"));
	}

	// This function will switch between tabs according to give tab name in amin
	// body frame
	// This Done
	// ** Changed the frames before checking for Tabs and also checked the ACTIVE
	// tab first due to multiple enteries of Tabs **//
	public void switchBetweenRadiologyTabs(WebDriver driver, String tabName) throws InterruptedException {

		List<WebElement> subTabParents = driver.findElements(By.xpath("//table[@id='tblTabsPages']/parent::*"));
		String temp = "";
		// Getting the current active tab id - from disease, precautions or
		// hospitalization
		for (int j = 0; j < subTabParents.size(); j++) {
			temp = PomReturnCssValue(driver, subTabParents.get(j), "display");
			if (temp.contains("block")) {
				temp = PomReturnValue(driver, subTabParents.get(j), "id");
				break;
			}
		}

		retryingFindClick1(driver,
				By.xpath("//*[@id=\"" + temp + "\"]//span[contains(@class, 'mid') and text() = '" + tabName + "']"));
	}

	// This function will switch between tabs according to give tab name in
	// DynamicDialogBox frame
	// This Done
	// ** Changed the frames before checking for Tabs **//
	public void switchBetweenDynamicDialogBoxTabs(WebDriver driver, String tabName) throws InterruptedException {
		String tabText = "";
		switchtoDynamicDialogBoxFromBH(driver);

		try {
			retryingFindClick1(driver, By
					.xpath("//*[@id=\"tblTabsPages\"]//span[contains(@class, 'mid') and text() = '" + tabName + "']"));
		} catch (Exception excep) {
			retryingFindClick1(driver,
					By.xpath("//*[@id=\"tblSubTabsPages\"]//span[contains(@class, 'mid') and contains(text() = '"
							+ tabName + "')]"));
		}
	}

	public void switchBetweenBillingTabs(WebDriver driver, String tabName) throws InterruptedException {
		SwitchToBillingtabsFrame(driver);
		retryingFindClick1(driver,
				By.xpath("//*[contains(@class,\"main-tabs\")]//li//a[contains(text(),\"" + tabName + "\")]"));

	}



	// --------------------------- End of Tab Switching Functions -------------------------- //


	// --------------------------------------------------------------------------------- //
	//                                  Popup Functions                                  //
	// --------------------------------------------------------------------------------- //

	// This function will click Cross icon on DynamicBHDialogBox
	public void clickCross(WebDriver driver) throws InterruptedException {

		driver.switchTo().defaultContent();
		try {
			retryingFindClick1(driver, By.xpath("//*[contains(@class, 'ui-icon ui-icon-closethick')]"));
		} catch (Exception e) {
			retryingFindClick1(driver, By.xpath("(//*[contains(@class, 'ui-icon ui-icon-closethick')])[2]"));
		}

	}

	// This function will click Cross icon on DynamicBHDialogBox
	public void clickCrossMainBody(WebDriver driver) throws InterruptedException {

		switchtomainBodyframe(driver);
		retryingFindClick1(driver, By.xpath("//*[contains(@class, 'ui-icon ui-icon-closethick')]"));

	}

	// This function will click Cross icon on DynamicBHDialogBox
	public void clickCross2(WebDriver driver) throws InterruptedException {

		driver.switchTo().defaultContent();
		retryingFindClick1(driver, By.xpath("(//*[contains(@class, 'close')]//span[contains(text(),\"close\")])[2]"));

	}

	// This function will click Cross icon on DynamicDialogBox
	public void clickCrossDynamicDialogBox(WebDriver driver) throws InterruptedException {

		switchtoDynamicDialogBoxFromBH(driver);
		retryingFindClick1(driver, By.xpath("//*[contains(@class, 'ui-icon ui-icon-closethick')]"));

	}

	// This function will click Cross icon on DynamicDialogBox
	public void clickCrossDynamicBHDialogBox(WebDriver driver) throws InterruptedException {

		switchtoDynamicBHdialogbox(driver);
		try {
			retryingFindClick1(driver, By.xpath("(//*[contains(@class, 'ui-icon ui-icon-closethick')])[2]"));
		} catch (Exception e) {
			retryingFindClick1(driver, By.xpath("//*[contains(@class, 'ui-icon ui-icon-closethick')]"));
		}

	}

	// This function will click 'Ok' button on prompt
	public void popUpclickOk(WebDriver driver) throws InterruptedException {

		try {
			retryingFindClick1(driver, By.xpath(
					"//button[contains(@class, 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only') and (text()='OK' or text()='Ok')]"));
		} catch (Exception excep) {
			try {
				retryingFindClick1(driver,
						By.xpath("//button[contains(@class, 'ButtonInactive') and (text()='OK' or text()='Ok')]"));
			} catch (Exception e) {
				retryingFindClick1(driver,
						By.xpath("//button[contains(@class, 'ButtonNormal') (text()='OK' or text()='Ok')]"));
			}

		}
	}

	// This function will click 'Yes' button on prompt
	public void popUpclickYes(WebDriver driver) throws InterruptedException {

		try {
			retryingFindClick1(driver, By.xpath(
					"//button[contains(@class, 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only') and text() = 'Yes']"));
		} catch (Exception excep) {
			try {
				retryingFindClick1(driver, By.xpath("//button[contains(@class, 'ButtonInactive') and text() = 'Yes']"));
			} catch (Exception e) {
				try {
					retryingFindClick1(driver,
							By.xpath("//button[contains(@class, 'ButtonNormal') and text() = 'Yes']"));
				} catch (Exception ex1) {
					retryingFindClick1(driver, By.xpath(
							"//button[contains(@class, 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ButtonNormal') and text() = 'Yes']"));
				}
			}
		}

	}

	// This function will click 'No' button on prompt
	public void popUpclickNo(WebDriver driver) throws InterruptedException {

		try {
			retryingFindClick1(driver, By.xpath(
					"//button[contains(@class, 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only') and text() = 'No']"));
		} catch (Exception excep) {
			try {
				retryingFindClick1(driver, By.xpath("//button[contains(@class, 'ButtonInactive') and text() = 'No']"));
			} catch (Exception e) {
				retryingFindClick1(driver, By.xpath("//button[contains(@class, 'ButtonNormal') and text() = 'No']"));
			}
		}

	}

	// This function will click 'Cancel' button on prompt
	public void popUpclickCancel(WebDriver driver) throws InterruptedException {

		try {
			retryingFindClick1(driver, By.xpath(
					"//button[contains(@class, 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only') and text() = 'Cancel']"));
		} catch (Exception excep) {
			try {
				retryingFindClick1(driver,
						By.xpath("//button[contains(@class, 'ButtonInactive') and text() = 'Cancel']"));
			} catch (Exception e) {
				retryingFindClick1(driver,
						By.xpath("//button[contains(@class, 'ButtonNormal') and text() = 'Cancel']"));
			}
		}

	}

	// This function will click 'Close' button on prompt
	public void popUpclickClose(WebDriver driver) throws InterruptedException {

		try {
			retryingFindClick1(driver, By.xpath(
					"//button[contains(@class, 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only') and text() = 'Close']"));
		} catch (Exception excep) {
			retryingFindClick1(driver, By.xpath("//button[contains(@class, 'ButtonInactive') and text() = 'Close']"));
		}

	}

	// This function will return text on prompt
	public String popUpReturnText(WebDriver driver) throws InterruptedException {
		String popUpText = "";
		popUpText = retryingReturnText(driver,
				By.xpath("//p[contains(@class, 'ui-dialog-content ui-widget-content')]"));
		return popUpText;
	}


	// -------------------------------- End of Popup Functions ----------------------------- //


	// --------------------------------------------------------------------------------- //
	//                                Side Tree Functions                                //
	// --------------------------------------------------------------------------------- //

	// This function will click provider note component (Obsolete)
	// This Done (Obsolete)
	// This function will be used to click any component from left tree but it's
	// very slow ( obsoleted)
	public void clickTreeComponent(WebDriver driver, String component, int loopNo)
			throws ParseException, InterruptedException {
		driver.switchTo().defaultContent();

		driver.switchTo().frame("fraCureMD_Patient_Menu");
		System.out.println("Finding " + component + " in provider note tree..");
		for (int i = 3; i <= loopNo; i++) {
			// Thread.sleep(1000);
			try {
				currentComponent = retryingReturnText(driver, By.id("webfx-tree-object-" + i + "-anchor"));
				// System.out.println(currentComponent);
			} catch (Exception e) {

			}
			if (currentComponent.contains(component)) {
				try {
					Actions action = new Actions(driver);
					try {
						Thread.sleep(2000);
						action.moveToElement(driver.findElement(By.xpath("//*[@id=\"webfx-tree-object-" + i + "\"]/a")))
						.doubleClick().perform();
					} catch (Exception ex) {
						action.moveToElement(driver.findElement(By.xpath("//*[@id=\"webfx-tree-object-" + i + "\"]/a")))
						.doubleClick().perform();
					}
				} catch (Exception e) {
					Actions action = new Actions(driver);
					action.moveToElement(driver.findElement(By.id("webfx-tree-object-" + i + "-anchor"))).doubleClick()
					.perform();

				}
				if (currentComponent.contains("Lab")) {
					retryingFindClick1(driver, By.xpath("//*[@id=\"webfx-tree-object-" + i + "\"]/a"));
				}
				System.out.println("" + component + " found in provider note tree..");
				break;
			}
			// System.out.println(""+component+" not found in provider note tree..");
		}

	}
	// This function will click plus icon on side tree according to components name.
	public static String HyperTreeSearchPlus(WebDriver driver, String Component) throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("fraCureMD_Patient_Menu");
		String id = "";
		String imgSrc = "";
		try {
			Pause(driver, driver.findElement(By.id("webfx-tree-object-3-plus")), "Visibility", 30);
		} catch (Exception excep) {

		}

		try {

			imgSrc = retryingReturnValue(driver,
					By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label/strong[contains(text(),'"
							+ Component + "')]/parent::label//preceding-sibling::img[contains(@src,\"plus\")]"),
					"src");
			id = retryingReturnValue(driver,
					By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label/strong[contains(text(),'"
							+ Component + "')]/parent::label//preceding-sibling::img[contains(@src,\"plus\")]"),
					"id");
		} catch (Exception excep) {

			imgSrc = retryingReturnValue(driver,
					By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label/strong[contains(text(),'"
							+ Component + "')]/parent::label//preceding-sibling::img[contains(@src,\"minus\")]"),
					"src");
			id = retryingReturnValue(driver,
					By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label/strong[contains(text(),'"
							+ Component + "')]/parent::label//preceding-sibling::img[contains(@src,\"minus\")]"),
					"id");

		}

		if (imgSrc.contains("plus")) {
			retryingFindClick1(driver, By.id(id));
		}

		String Parts[] = id.split("-");
		String number = Parts[3];
		String ParentID = "webfx-tree-object-" + number + "-cont";

		return ParentID;
	}

	// This function will click plus icon on side tree according to components name.
	public static String HyperTreeSearchPlus(WebDriver driver, String ParentID, String Component)
			throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("fraCureMD_Patient_Menu");
		String id = "";
		String imgSrc = "";
		try {
			try {

				imgSrc = retryingReturnValue(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label/strong[contains(text(),'"
								+ Component + "')]/parent::label//preceding-sibling::img[contains(@src,\"plus\")]"),
						"src");
				id = retryingReturnValue(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label/strong[contains(text(),'"
								+ Component + "')]/parent::label//preceding-sibling::img[contains(@src,\"plus\")]"),
						"id");

			} catch (Exception e) {

				imgSrc = retryingReturnValue(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label[contains(text(),'" + Component
								+ "')]//preceding-sibling::img[contains(@src,\"plus\")]"),
						"src");
				id = retryingReturnValue(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label[contains(text(),'" + Component
								+ "')]//preceding-sibling::img[contains(@src,\"plus\")]"),
						"id");

			}
		} catch (Exception excep) {
			try {

				imgSrc = retryingReturnValue(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label/strong[contains(text(),'"
								+ Component + "')]/parent::label//preceding-sibling::img[contains(@src,\"minus\")]"),
						"src");
				id = retryingReturnValue(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label/strong[contains(text(),'"
								+ Component + "')]/parent::label//preceding-sibling::img[contains(@src,\"minus\")]"),
						"id");

			} catch (Exception e) {

				imgSrc = retryingReturnValue(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label[contains(text(),'" + Component
								+ "')]//preceding-sibling::img[contains(@src,\"minus\")]"),
						"src");
				id = retryingReturnValue(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label[contains(text(),'" + Component
								+ "')]//preceding-sibling::img[contains(@src,\"minus\")]"),
						"id");

			}
		}

		if (imgSrc.contains("plus")) {
			retryingFindClick1(driver, By.id(id));
		}

		String Parts[] = id.split("-");
		String number = Parts[3];
		ParentID = "webfx-tree-object-" + number + "-cont";

		return ParentID;

	}

	// This function will click anchor side tree according to components name.
	public static void HyperTreeSearchAnchor(WebDriver driver, String Component) throws InterruptedException {

		switchtofraCureMD_Patient_Menu(driver);

		try {
			try {
				retryingFindClick1(driver,
						By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label//strong[contains(text(),'"
								+ Component + "')]/parent::label/following-sibling::a"));

			} catch (Exception excep) {
				retryingFindClick1(driver,
						By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label//strong[contains(text(),'"
								+ Component + "')]/parent::label"));
			}
		} catch (Exception e) {
			try {
				try {
					retryingFindClick1(driver,
							By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label[contains(text(),'"
									+ Component + "')]/following-sibling::a"));
				} catch (Exception excep) {
					retryingFindClick1(driver,
							By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label[contains(text(),'"
									+ Component + "')]"));
				}
			} catch (Exception ex) {
				try {
					retryingFindClick1(driver,
							By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label//span[contains(text(),'"
									+ Component + "')]"));
				} catch (Exception exception) {
					retryingFindClick1(driver,
							By.xpath("//div[@id=\"webfx-tree-object-2-cont\"]/descendant::label[contains(text(),'"
									+ Component + "')]"));
				}
			}
		}

	}

	// This function will click anchor side tree according to components name.
	public static void HyperTreeSearchAnchor(WebDriver driver, String ParentID, String Component)
			throws InterruptedException {

		switchtofraCureMD_Patient_Menu(driver);

		try {
			try {
				retryingFindClick1(driver,
						By.xpath("//div[@id=\"" + ParentID + "\"]/descendant::label//strong[contains(text(),'"
								+ Component + "')]/parent::label/following-sibling::a"));

			} catch (Exception excep) {
				retryingFindClick1(driver, By.xpath("//div[@id=\"" + ParentID
						+ "\"]/descendant::label//strong[contains(text(),'" + Component + "')]/parent::label"));
			}
		} catch (Exception e) {
			try {
				try {
					retryingFindClick1(driver, By.xpath("//div[@id=\"" + ParentID
							+ "\"]/descendant::label[contains(text(),'" + Component + "')]/following-sibling::a"));
				} catch (Exception excep) {
					retryingFindClick1(driver, By.xpath(
							"//div[@id=\"" + ParentID + "\"]/descendant::label[contains(text(),'" + Component + "')]"));
				}
			} catch (Exception ex) {
				try {
					retryingFindClick1(driver, By.xpath("//div[@id=\"" + ParentID
							+ "\"]/descendant::label//span[contains(text(),'" + Component + "')]"));
				} catch (Exception exception) {
					retryingFindClick1(driver, By.xpath(
							"//div[@id=\"" + ParentID + "\"]/descendant::label[contains(text(),'" + Component + "')]"));

				}
			}
		}

	}

	// Obsolete
	// This Done (Obsolete)
	// This function is used to click plus sign of left tree component and it will
	// return id of that that particular component
	public static String HyperloopPlus(WebDriver driver, String Component) throws InterruptedException {

		driver.switchTo().defaultContent();
		driver.switchTo().frame("fraCureMD_Patient_Menu");

		String id = "";
		String value = "";
		String Parts[];
		String number = "";

		WebElement rootWebElement = driver.findElement(By.id("webfx-tree-object-2-cont"));
		List<WebElement> childs = rootWebElement.findElements(By.xpath("..//div"));

		for (int i = 1; i <= childs.size(); i++) {
			if (i % 2 == 0) {
				continue;
			}
			try {
				id = retryingReturnValue(driver, By.xpath("//*[@id=\"webfx-tree-object-2-cont\"]/div[" + i + "]"),
						"id");
				value = retryingReturnText(driver,
						By.xpath("//*[@id=\"webfx-tree-object-2-cont\"]/div[" + i + "]/label"));

				if (value.equals(Component)) {
					Parts = id.split("-");
					number = Parts[3];
					break;
				}
				// added
				else {
					id = retryingReturnValue(driver, By.xpath("//*[@id=\"clinicalNoteTree-cont\"]/div[" + i + "]"),
							"id");
					value = retryingReturnText(driver,
							By.xpath("//*[@id=\"clinicalNoteTree-cont\"]/div[" + i + "]/label"));

					if (value.equals(Component)) {
						Parts = id.split("-");
						number = Parts[3];
						break;
					}
				} /////
			} catch (Exception e) {

			}

		}

		id = "webfx-tree-object-" + number + "-plus";

		retryingFindClick1(driver, By.id(id));
		Thread.sleep(2000);

		return number;
	}

	// Obsolete
	// This Done (Obsolete)
	// This function will search and click sub-component in hierarchy which we will
	// provide in Component parameter
	// To use this function we need to run HyperloopPlus function which will return
	// id of parent componenet which we will provide in number parameter
	public static String HyperloopPlusAnchor(WebDriver driver, String number, String Component)
			throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("fraCureMD_Patient_Menu");

		String value = "";
		String Parts[];

		String id = "";

		id = "webfx-tree-object-" + number + "-cont";

		WebElement rootChildWebElement = driver.findElement(By.id(id));
		List<WebElement> childs2 = rootChildWebElement.findElements(By.xpath(".//*"));

		for (int i = 1; i <= childs2.size(); i++) {
			if (i % 2 == 0) {
				continue;
			}
			try {
				id = retryingReturnValue(driver,
						By.xpath("//*[@id=\"webfx-tree-object-" + number + "-cont\"]/div[" + i + "]"), "id");
				value = retryingReturnText(driver,
						By.xpath("//*[@id=\"webfx-tree-object-" + number + "-cont\"]/div[" + i + "]/label"));

				if (value.contains(Component)) {
					Parts = id.split("-");
					number = Parts[3];

					id = "webfx-tree-object-" + number + "-plus";
					retryingFindClick1(driver, By.id(id));

					break;
				}
			} catch (Exception e) {

			}

		}

		return number;
	}

	// Obsolete
	// This Done (Obsolete)
	public static String HyperloopAnchor(WebDriver driver, String Component) throws InterruptedException {

		driver.switchTo().defaultContent();
		driver.switchTo().frame("fraCureMD_Patient_Menu");

		String id = "";
		String value = "";
		String Parts[];
		String number = "";

		WebElement rootWebElement = driver.findElement(By.id("webfx-tree-object-2-cont"));
		List<WebElement> childs = rootWebElement.findElements(By.xpath("..//div"));

		for (int i = 1; i <= childs.size(); i++) {
			if (i % 2 == 0) {
				continue;
			}
			try {
				id = retryingReturnValue(driver, By.xpath("//*[@id=\"webfx-tree-object-2-cont\"]/div[" + i + "]"),
						"id");
				value = retryingReturnText(driver,
						By.xpath("//*[@id=\"webfx-tree-object-2-cont\"]/div[" + i + "]/label"));

				if (value.contains(Component)) {
					Parts = id.split("-");
					number = Parts[3];
					break;
				}
			} catch (Exception e) {

			}

		}

		id = "webfx-tree-object-" + number + "-anchor";
		Thread.sleep(2000);
		System.out.println("id:" + id);
		retryingFindClick1(driver, By.id(id));
		Thread.sleep(2000);

		return number;
	}

	// Obsolete
	// This Done (Obsolete)
	// This function will search and click sub-component in hierarchy which we will
	// provide in Component parameter
	// To use this function we need to run HyperloopPlus function which will return
	// id of parent componenet which we will provide in number parameter
	public static String HyperloopAnchor(WebDriver driver, String number, String Component)
			throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("fraCureMD_Patient_Menu");

		String value = "";
		String Parts[];

		String id = "";

		id = "webfx-tree-object-" + number + "-cont";

		WebElement rootChildWebElement = driver.findElement(By.id(id));
		List<WebElement> childs2 = rootChildWebElement.findElements(By.xpath(".//*"));

		for (int i = 1; i <= childs2.size(); i++) {
			if (i % 2 == 0) {
				continue;
			}
			try {
				id = retryingReturnValue(driver,
						By.xpath("//*[@id=\"webfx-tree-object-" + number + "-cont\"]/div[" + i + "]"), "id");
				value = retryingReturnText(driver,
						By.xpath("//*[@id=\"webfx-tree-object-" + number + "-cont\"]/div[" + i + "]/label"));

				if (value.contains(Component)) {
					retryingFindClick1(driver,
							By.xpath("//*[@id=\"webfx-tree-object-" + number + "-cont\"]/div[" + i + "]/label"));

					Parts = id.split("-");
					number = Parts[3];

					break;
				}
			} catch (Exception e) {

			}

		}
		id = "webfx-tree-object-" + number + "-plus";
		return number;
	}

	// Obsolete
	// This Done (Obsolete)
	public static void HyperloopAnchorChild(WebDriver driver, String number, String Component)
			throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("fraCureMD_Patient_Menu");

		String value = "";
		String Parts[];

		String id = "";

		id = "webfx-tree-object-" + number + "-cont";

		System.out.println("Component id:" + id);

		WebElement rootChildWebElement = driver.findElement(By.id(id));
		List<WebElement> childs2 = rootChildWebElement.findElements(By.xpath(".//*"));

		System.out.println("Child Size:" + childs2.size());

		for (int i = 1; i <= childs2.size(); i++) {
			if (i % 2 == 0) {
				continue;
			}
			try {

				value = retryingReturnText(driver,
						By.xpath("//*[@id=\"webfx-tree-object-" + number + "-cont\"]/div[" + i + "]/label"));

				if (value.contains(Component)) {
					retryingFindClick1(driver,
							By.xpath("//*[@id=\"webfx-tree-object-" + number + "-cont\"]/div[" + i + "]/label"));

					break;
				}
			} catch (Exception e) {

			}

		}

	}

	// This function will click tree component(Obsolete)
	// This (Obsolete)
	public void clickTreeComponent(WebDriver driver, String component) throws ParseException, InterruptedException {
		driver.switchTo().defaultContent();

		driver.switchTo().frame("fraCureMD_Patient_Menu");
		System.out.println("Finding " + component + " in provider note tree..");
		for (int i = 5; i <= 60; i++) {
			Thread.sleep(1000);
			try {
				currentComponent = retryingReturnText(driver, By.id("webfx-tree-object-" + i + "-anchor"));

			} catch (Exception e) {

			}
			if (currentComponent.equals(component)) {
				try {
					Actions action = new Actions(driver);
					action.moveToElement(driver.findElement(By.xpath("//*[@id=\"webfx-tree-object-" + i + "\"]/a")))
					.doubleClick().perform();
				} catch (Exception e) {
					Actions action = new Actions(driver);
					action.moveToElement(driver.findElement(By.id("webfx-tree-object-" + i + "-anchor"))).doubleClick()
					.perform();
				}
				System.out.println("" + component + " found in provider note tree..");
				break;
			}
		}

	}

	// -------------------------- End of Side Tree Functions ------------------------- //


	// --------------------------------------------------------------------------------- //
	//                                Provider note Functions                                //
	// --------------------------------------------------------------------------------- //

	// This function will click provider note component
	// This Done //Click provider note component according to its heading.
	public void clickProviderNoteComponent(WebDriver driver, String component) throws InterruptedException {

		switchtomainBodyframe(driver);

		retryingFindClick1(driver,
				By.xpath("(//*[@id=\"divEditArea\"]//div//table//font[contains(text(),\"" + component + "\")])[1]"));

		switchtoDynamicBHdialogbox(driver);

	}

	// This function will click provider note component according to specified
	// position e.g 1,2,3 etc
	// This Done //Click provider note component according to its heading and
	// position on provider note e.g 1,2,3 etc
	public void clickProviderNoteComponent(WebDriver driver, String component, int position)
			throws InterruptedException {

		switchtomainBodyframe(driver);

		retryingFindClick1(driver, By.xpath("(//*[@id=\"divEditArea\"]//div//table//font[contains(text(),\"" + component
				+ "\")])[" + position + "]"));

		switchtoDynamicBHdialogbox(driver);

	}

	// This function will return header text of provider note component
	// This Done //Get provider note component text according to its heading
	public String getProviderNoteComponentSoapText(WebDriver driver, String component) throws InterruptedException {

		String soapText = "";

		switchtomainBodyframe(driver);
		Thread.sleep(2000);

		try {
			soapText = retryingReturnText(driver, By
					.xpath("(//*[@id=\"divEditArea\"]//div//table//font[contains(text(),\"" + component + "\")])[1]"));

			soapText = soapText + " "
					+ retryingReturnText(driver, By.xpath("//*[@id=\"divEditArea\"]//font[contains(text(),'" + component
							+ "')]/ancestor::tbody[1]//label[@id='lbl']/font"));
		} catch (Exception e) {
			soapText = retryingReturnText(driver, By.xpath(
					"(//*[@id=\"divEditArea\"]//div//table//font/b[contains(text(),\"" + component + "\")])[1]"));

			soapText = soapText + " "
					+ retryingReturnText(driver, By.xpath("//*[@id=\"divEditArea\"]//font/b[contains(text(),'"
							+ component + "')]/ancestor::tbody[1]//label[@id='lbl']/font"));

		}

		return soapText;

	}

	// This function will return header text of provider note component according to
	// its position
	// This Done //Get provider note component text according to its heading and
	// position on provider note e.g 1,2,3 etc
	public String getProviderNoteComponentSoapText(WebDriver driver, String component, String position)
			throws InterruptedException {

		String soapText = "";

		switchtomainBodyframe(driver);

		soapText = retryingReturnText(driver, By.xpath("(//*[@id=\"divEditArea\"]//div//table//font[contains(text(),\""
				+ component + "\")])[" + position + "]"));

		return soapText;

	}



	// -------------------------- End of Provider note Functions ------------------------- //


	// --------------------------------------------------------------------------------- //
	//                                Scheduler Functions                                //
	// --------------------------------------------------------------------------------- //

	// This function will open and return the free slot on scheduler.
	// This Done
	// This function is used to book an appointment in schedular
	// This function will land you on Hotlist page
	// Returning available slot values so available slot could be used again
	public static String getSlot(WebDriver driver) throws InterruptedException {
		// to use this function you have to be on Scheduler slots

		String SlotId = "";
		switchtoIframe1(driver);

		try {
			Thread.sleep(3000);
			SlotId = retryingReturnValue(driver, By.xpath(
					"(//*[@id=\"ctl00_CP1_UcSchedules1_GridView1\"]//span[contains(@class,'LabelBlue LabelSmall') and text()='1' ]/parent::td/following-sibling::td[1]/parent::tr)[1]"),
					"id");
			retryingFindClick(driver, By.xpath(
					"(//*[@id=\"ctl00_CP1_UcSchedules1_GridView1\"]//span[contains(@class,'LabelBlue LabelSmall') and text()='1' ]/parent::td/following-sibling::td[1])[1]"));
			System.out.println("Slot found in 1st try " + SlotId);
		} catch (Exception excep) {
			//retryingFindClick(driver, By.id("ctl00_CP1_imgbtnLast"));
			System.out.println("Slot found in catch " + SlotId);
		}

		driver.switchTo().frame("ifrmBookSearchedAppt");
		return SlotId;
	}




	// This function will open first empty slot on scheduler and return its id,time
	// and AM/PM hand
	// This Done
	// This function is used to book an appointment in schedular
	// This function will land you on Hotlist page
	// Returning available slot values and time so available slot could be used
	// again
	public static ArrayList<String> getSlotTime(WebDriver driver) throws InterruptedException {

		ArrayList<String> slotTime = new ArrayList<String>();

		String SlotId = "";
		String SlotHrs = "";
		String SlotMins = "";
		String SlotTime = "";
		String SlotHand = "";

		switchtoIframe1(driver);

		try {

			SlotId = retryingReturnValue(driver, By.xpath(
					"(//*[@id=\"ctl00_CP1_UcSchedules1_GridView1\"]//span[contains(@class,'LabelBlue LabelSmall') and text()='1' ]/parent::td/following-sibling::td[1]/parent::tr)[1]"),
					"id");
			retryingFindClick(driver, By.xpath(
					"(//*[@id=\"ctl00_CP1_UcSchedules1_GridView1\"]//span[contains(@class,'LabelBlue LabelSmall') and text()='1' ]/parent::td/following-sibling::td[1])[1]"));

		} catch (Exception excep) {
			retryingFindClick(driver, By.id("ctl00_CP1_imgbtnLast"));

		}

		SlotHrs = retryingReturnText(driver, By.xpath(
				"((//*[@id=\"ctl00_CP1_UcSchedules1_GridView1\"]//span[contains(@class,'LabelBlue LabelSmall') and text()='1' ]//parent::td//following-sibling::td[1]//parent::tr)//td)[text()='1'] "));

		SlotMins = retryingReturnValue(driver, By.id(SlotId + "_Button1"), "value");

		if (SlotMins.equals("AM") || SlotMins.equals("PM")) {

			SlotMins = "00";

		}

		SlotTime = SlotHrs + ":" + SlotMins;

		if (Integer.parseInt(SlotHrs) > 11) {
			SlotHand = "PM";
		} else {
			SlotHand = "AM";
		}

		driver.switchTo().frame("ifrmBookSearchedAppt");
		// Here in SlotTime and SlotHand we are adding time of slot and AM/PM value to
		// use further
		slotTime.add(SlotId);
		slotTime.add(SlotTime);
		slotTime.add(SlotHand);

		// Here we are returning all the added data in array for further use
		return slotTime;
	}

	// This function will open empty slot on specified time slot on scheduler and
	// return its id,time and AM/PM hand
	// This Done
	// This function is used to book an appointment in schedular
	// This function will land you on Hotlist page
	// Returning available slot values and time so available slot could be used
	// again
	public static ArrayList<String> getSlotWithTime(WebDriver driver, String TimeSlot) throws InterruptedException {
		ArrayList<String> slotTime = new ArrayList<String>();

		String TimeSlotParts[];
		String SlotId = "";
		String SlotHrs = "";
		String SlotMins = "";
		String SlotTime = "";
		String SlotHand = "";

		switchtoIframe1(driver);

		try {

			SlotId = retryingReturnValue(driver, By.xpath(
					"(//*[@id=\"ctl00_CP1_UcSchedules1_GridView1\"]//span[contains(@class,'LabelBlue LabelSmall') and text()='1'  and contains(@onclick,'"
							+ TimeSlot + "') ]/parent::td/following-sibling::td[1]/parent::tr)[1]"),
					"id");
			retryingFindClick(driver, By.xpath(
					"(//*[@id=\"ctl00_CP1_UcSchedules1_GridView1\"]//span[contains(@class,'LabelBlue LabelSmall') and text()='1'  and contains(@onclick,'"
							+ TimeSlot + "') ]/parent::td/following-sibling::td[1])[1]"));

		} catch (Exception excep) {
			Assert.fail(TimeSlot + " slot not available");

		}

		TimeSlotParts = TimeSlot.split(":");

		SlotHrs = TimeSlotParts[0];

		SlotMins = retryingReturnValue(driver, By.id(SlotId + "_Button1"), "value");

		if (SlotMins.equals("AM") || SlotMins.equals("PM")) {

			SlotMins = "00";

		}

		SlotTime = SlotHrs + ":" + SlotMins;

		if (Integer.parseInt(SlotHrs) > 11) {
			SlotHand = "PM";
		} else {
			SlotHand = "AM";
		}

		driver.switchTo().frame("ifrmBookSearchedAppt");
		// Here in SlotTime and SlotHand we are adding time of slot and AM/PM value to
		// use further
		slotTime.add(SlotId);
		slotTime.add(SlotTime);
		slotTime.add(SlotHand);

		// Here we are returning all the added data in array for further use
		return slotTime;
	}

	// This function will open first empty slot on scheduler and return its id,time
	// and AM/PM hand
	// This Done
	// This function is used to book an appointment in schedular
	// This function will land you on Hotlist page
	// Returning available slot values and time so available slot could be used
	// again
	public static String getSlotWeekly(WebDriver driver) throws InterruptedException {

		// ArrayList<String> SlotId = new ArrayList<String>();
		switchtomainBodyframe(driver);
		Date today;
		String strDate;
		DateFormat dateFormat;
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		today = new Date();
		strDate = dateFormat.format(today);
		String TDate = TodaysDate();

		String id = retryingReturnValue(driver,
				By.xpath("//*[@id=\"upnlPanels\"]//span[contains(text(),\"" + strDate + "\")]"), "id");

		String parts[] = id.split("g");

		String frameid = "ifrmSchedule" + parts[1];
		System.out.println(frameid);

		String SlotId = "";

		driver.switchTo().frame(frameid);

		SlotId = retryingReturnValue(driver, By.xpath(
				"(//*[@id=\"ctl00_CP1_UCMV1_GV1\"]//span[contains(@class,'LabelBlue') and text()='1' ]/parent::td/following-sibling::td[1])[contains(@onclick,\""
						+ strDate + "\")][1]//preceding-sibling::td"),
				"id");
		retryingFindClick(driver, By.xpath(
				"(//*[@id=\"ctl00_CP1_UCMV1_GV1\"]//span[contains(@class,'LabelBlue') and text()='1' ]/parent::td/following-sibling::td[1])[contains(@onclick,\""
						+ strDate + "\")][1]"));

		return SlotId;

	}

	// This function will open first empty slot on scheduler and return its id,time
	// and AM/PM hand
	// This Done
	// This function is used to book an appointment in schedular
	// This function will land you on Hotlist page
	// Returning available slot values and time so available slot could be used
	// again
	public static String getSlotMultiple(WebDriver driver) throws InterruptedException {

		// ArrayList<String> SlotId = new ArrayList<String>();
		switchtomainBodyframe(driver);
		Date today;
		String strDate;
		DateFormat dateFormat;
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		today = new Date();
		strDate = dateFormat.format(today);
		String TDate = TodaysDate();
		System.out.println(strDate);
		System.out.println(TDate);
		String id = retryingReturnValue(driver,
				By.xpath("//*[@id=\"upnlPanels\"]//span[contains(text(),\"" + strDate + "\")]"), "id");

		String parts[] = id.split("g");

		String frameid = "ifrmSchedule" + parts[1];
		System.out.println(frameid);

		String SlotId = "";

		driver.switchTo().frame(frameid);

		SlotId = retryingReturnValue(driver, By.xpath(
				"(//*[@id=\"ctl00_CP1_UCMV1_GV1\"]//span[contains(@class,'LabelBlue') and text()='1' ]/parent::td/following-sibling::td[1])[contains(@onclick,\""
						+ strDate + "\")][1]//preceding-sibling::td"),
				"id");
		retryingFindClick(driver, By.xpath(
				"(//*[@id=\"ctl00_CP1_UCMV1_GV1\"]//span[contains(@class,'LabelBlue') and text()='1' ]/parent::td/following-sibling::td[1])[contains(@onclick,\""
						+ strDate + "\")][1]"));

		return SlotId;

	}

	// This function will hover or click on specified date of side calender
	// This Done
	// This function is used to hover or click specific day on side calender
	public static void ClickCalenderDate(WebDriver driver, String DateToAction, String action)
			throws InterruptedException {

		switchtoifrmBookSearchedAppt(driver);

		if (action.contains("Hover")) {

			retryingFindHover(driver, By.xpath(
					"//*[@id=\"ctl00_CP1_UpdatePanel2\"]/table/tbody/tr/td/table/tbody/tr/td/div[contains(@onclick,\""
							+ DateToAction + "\")]"));

		}

		else if (action.contains("Click")) {

			retryingFindClick(driver, By.xpath(
					"//*[@id=\"ctl00_CP1_UpdatePanel2\"]/table/tbody/tr/td/table/tbody/tr/td/div[contains(@onclick,\""
							+ DateToAction + "\")]"));

		}

	}



	// -------------------------- End of Scheduler Functions ------------------------- //

	// --------------------------------------------------------------------------------- //
	//                           Excel/Files related Functions                           //
	// --------------------------------------------------------------------------------- //

	// This function will return latest file name from download directory
	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	// This function will verify latest downloaded file and return boolean
	// (true/false)
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().contains(fileName))
				return flag = true;
		}

		return flag;
	}

	// This function will return Data in ArrayList of specified file name & sheet
	// name of Excel file
	public ArrayList<String> readExcel(String fileName, String sheetName) throws IOException {
		String filePath = System.getProperty("user.dir");
		// Create an object of File class to open xlsx file
		ArrayList<String> ExcelData = new ArrayList<String>();
		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			guru99Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			guru99Workbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name

		Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it

		for (int i = 1; i < rowCount + 1; i++) {

			Row row = guru99Sheet.getRow(i);

			// Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {

				// Print Excel data in console

				// System.out.print(row.getCell(j)+"\n");
				ExcelData.add(row.getCell(j).toString());
			}

		}
		return ExcelData;
	}

	public String GetPDFText(String src) {
		//load the pdf document
		boolean flag = false;

		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;

		try {
			URL url = new URL(src);
			BufferedInputStream file = new BufferedInputStream(url.openStream());
			PDFParser parser = new PDFParser(file);

			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(1);

			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed "+e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		System.out.println(parsedText);
		return parsedText;

	}

	// This function is returning comma separated list of provided list of db
	// columns in excel
	// This function will return parsed list of values of comma seprated list
	public ArrayList<String> getColumn(String colVar) {

		ArrayList<String> result = new ArrayList<String>();
		String[] dbColumns = colVar.split(",");

		for (int i = 0; i < dbColumns.length; i++) {
			result.add(dbColumns[i]);
		}

		return result;

	}

	// This function will capture screenshot
	// for capturing screen shot
	public static void captureScreenshot(WebDriver driver, String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));

			System.out.println("Screenshot taken");
		} catch (Exception e) {

			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}




	// ----------------------- End of Excel/Files related Functions ---------------------- //

	// --------------------------------------------------------------------------------- //
	//                               SaltString Functions                                //
	// --------------------------------------------------------------------------------- //

	public String getSaltStringSSN() {
		String SALTCHARS = "0123456789";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
		// This function is returning random string alphabet string
	}

	// This function will return salt string of alphabets.
	// This function is returning random string alphabet string
	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	// This function will return salt string of numbers.
	// This function is returning random string number string
	public String getSaltStringNumbers() {
		String SALTCHARS = "1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 9) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	// This function will return salt string of alphabets of specified length
	public String getSaltStringCustom(int length) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < length) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
		// This function is returning random string alphabet string
	}


	// ----------------------- End of SaltString Functions ---------------------- //


	// --------------------------------------------------------------------------------- //
	//                              Miscellaneous Functions                              //
	// --------------------------------------------------------------------------------- //


	public ArrayList<Integer> StartClock() throws ParseException {
		ArrayList<Integer> StartTime = new ArrayList<Integer>();
		String stime = getCurrentTime();
		System.out.println("Start Time:" + stime);
		String StartParts[] = stime.split(":");
		int shr = Integer.parseInt(StartParts[0]);
		int smin = Integer.parseInt(StartParts[1]);
		int ssec = Integer.parseInt(StartParts[2]);

		StartTime.add(shr);
		StartTime.add(smin);
		StartTime.add(ssec);

		return StartTime;
	}

	public void StopClock(ArrayList<Integer> StartTime) throws ParseException {
		int shr = StartTime.get(0);
		int smin = StartTime.get(1);
		int ssec = StartTime.get(2);

		String etime = getCurrentTime();
		System.out.println("End Time:" + etime);
		String EndParts[] = etime.split(":");
		int ehr = Integer.parseInt(EndParts[0]);
		int emin = Integer.parseInt(EndParts[1]);
		int esec = Integer.parseInt(EndParts[2]);

		if (emin < smin) {
			int addmin1 = 60 - smin;
			int addmin2 = smin - emin;
			emin = emin + addmin1 + addmin2;
		}

		if (esec < ssec) {
			int addsec1 = 60 - ssec;
			int addsec2 = ssec - esec;
			esec = esec + addsec1 + addsec2;
		}

		int thr = ehr - shr;
		int tmin = emin - smin;
		int tsec = esec - ssec;

		String TotalTimeTaken = thr + ":" + tmin + ":" + tsec;
		System.out.println("Total Time Taken:" + TotalTimeTaken);

	}

	// This will solve captcha at the start of v10G Application
	public String captcha_solutionString(String captcha_text) {
		logger.info("Captcha Entry point");

		int result = 0;
		String a = captcha_text;

		char[] ch = a.toCharArray();

		int a1 = Character.getNumericValue(ch[0]);
		int a2 = Character.getNumericValue(ch[1]);
		int a3 = Character.getNumericValue(ch[5]);
		int a4 = Character.getNumericValue(ch[6]);
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb1.append(a1);
		sb1.append(a2);

		String s1 = sb1.toString();

		sb2.append(a3);
		sb2.append(a4);
		String s2 = sb2.toString();

		int a123 = Integer.parseInt(s1);
		int a124 = Integer.parseInt(s2);

		char fun = ch[3];
		if (fun == '+') {
			result = a123 + a124;

		} else if (fun == '-') {
			result = a123 + a124;

		}

		String str = String.valueOf(result);

		logger.info(result);
		return str;
	}

	// This function will select provider from JQuery dropdown
	public static void ProviderByName(WebDriver driver, String provider) throws InterruptedException {

		retryingFindClick1(driver, By.xpath("//*[@id=\"JQdropdown\"]//a[contains(text(),'" + provider + "')]"));

	}

	// This function will import diagnosis on diagnosis module.
	// This Done
	// It will be used to import diagnosis from library in diagnosis popup
	public void importDiagnosis(WebDriver driver) throws InterruptedException {
		try {
			Pause(driver, driver.findElement(By.id("0")), "Visibility", 50);
		} catch (Exception excep) {

		}
		Thread.sleep(3000);
		try {
			// driver.findElement(By.id("0")).click();
			retryingFindClick1(driver, By.id("0"));
			logger.info("Diagnosis clicked");
		} catch (Exception ex) {

			try {
				retryingFindClick1(driver, By.id("importLinkNoResult"));
				logger.info("Search library link clicked");
				try {
					Pause(driver, driver.findElement(By.id("0")), "Visibility", 50);
				} catch (Exception excep) {

				}

				driver.findElement(By.id("0")).click();
				try {
					Pause(driver, driver.findElement(By.id("0")), "Alert", 20);
				} catch (Exception ex2) {

				}

				this.popUpclickOk(driver);
				logger.info("Ok button clicked");
				driver.findElement(By.id("0")).click();

			} catch (Exception ex1) {

			}
			try {
				this.popUpclickOk(driver);
				logger.info("Ok button clicked");
			} catch (Exception e) {

			}
		}

	}

	// This function will return Credentials in ArrayList from XML file.
	// It will be used to get url, username and password from xml file
	public ArrayList<String> setUsernamePassword() throws ParserConfigurationException, SAXException, IOException {
		// XML Chunk Starts Here
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		// Document document = builder.parse(new File("C://Users//Zarrar
		// Khan//workspace//Test//Credentials//Credentials.xml"));

		String filePath = System.getProperty("user.dir") + "\\Credentials\\Credentials.xml";

		Document document = builder.parse(new File(filePath));
		org.w3c.dom.Element rootElement = document.getDocumentElement();
		String URL = rootElement.getElementsByTagName("URL").item(0).getTextContent();
		String Username = rootElement.getElementsByTagName("Username").item(0).getTextContent();
		String Password = rootElement.getElementsByTagName("Password").item(0).getTextContent();
		String DBConnectionString = rootElement.getElementsByTagName("DBConnectionString").item(0).getTextContent();
		// XML Chunk Ends Here

		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add(URL);
		credentials.add(Username);
		credentials.add(Password);
		credentials.add(DBConnectionString);
		return credentials;
	}


	// ------------------------ End of Miscellaneous Functions ------------------------ //

}