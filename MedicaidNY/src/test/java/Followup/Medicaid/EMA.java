
package Followup.Medicaid;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import Objects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.allure.annotations.*;

@Listeners({ TestListener.class })
public class EMA{

	Logger logger = Logger.getLogger("EMA");
	public static Xls_Reader excelfile = null;
	public static String downloadPath = System.getProperty("user.dir");
	TestListener tlistener = new TestListener();
	WebDriver driver;
	Utility utility;
	EMAObjects emaObjects ;
	String webURL, username, password,patientLastname,patientFirstName,text,recordNo,dateFormat,newFileName,Shortname,client,previousClient="test",currentClient,date;
	File newfile;
	Boolean multipleBit=false,bit2=true, signinBit=false,urlBit=true,onetime=false;
	ChromeOptions options;
	int rowNumber=1;
	String sheetName="Sheet1";

	@Test(priority = 1)
	public void preRec() {

		emaObjects = new EMAObjects(driver);

		WebDriverManager.chromedriver().setup();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);	
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		options.setExperimentalOption("useAutomationExtension", false);
		driver= new ChromeDriver(options);
		PropertyConfigurator.configure("Log4j.properties");
		utility= new Utility();
		emaObjects = new EMAObjects(driver);

	}

	//-------------- Test case --------------/
	@Test(priority = 2, dataProvider = "get_print_data")
	public void Action(String shortname, String clientName, String URL, String Login, String Pass, String accountNo, String patient,String dob, String IAPPTID, String dos, String cpts, String icds, String planBalance, String patBalance,String insurance,  String claimSubDate, String patID, String recPaymentDate, String priority, String billProvider, String serviceLocation, String probList, String lastApptNote, String lastApptDate, String lastApptUSer, String Status ) throws Exception {
		
		rowNumber++;
		if(Status=="") {
			Shortname=shortname;
			webURL = URL;
			username= Login;
			password = Pass;
			client = clientName;
			currentClient=clientName;

			logger.info("**** UserName: "+username+"****");
			logger.info("**** Password: "+password+"****");
			logger.info("**** Patient: "+patient+"****");
			logger.info("**** DOS: "+dos+"****");
			logger.info("**** DOB: "+dob+"****");

			logger.info(currentClient);
			logger.info(previousClient);
			logger.info(URL);
			if(urlBit)
			{
				try {
					driver.get(webURL);
					logger.info("URL opened");
					driver.manage().window().maximize();
					urlBit=false;
				}
				catch(Exception excep) {
					excelfile.setCellData(sheetName,"Status",rowNumber,"URL not accessible");
					Assert.fail("URL not accessible");
				}
			}
			else {
				logger.info("URL bit is false");
			}

			Thread.sleep(4000);
			if(!previousClient.contains(currentClient))
			{
				try {
					
				
					utility.PomFindHover(driver, driver.findElement(By.id("welcome-menu-collapsed-name")));
					utility.Pause(driver, emaObjects.signOutBtn, "Visibility", 15);
					utility.PomFindClick(driver, emaObjects.signOutBtn);
					logger.info("Sign out tab clicked");
					onetime=true;
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					utility.Pause(driver, emaObjects.UserNameField, "Visibility", 15);
				}
				catch(Exception e) {

				}
				utility.PomFindField(driver, emaObjects.UserNameField, username);
				logger.info("Username entered");
				utility.PomFindField(driver, emaObjects.PasswordField, password);
				logger.info("Password entered");
				utility.PomFindClick(driver, emaObjects.LoginButton);
				logger.info("Login clicked");
				try {
					utility.Pause(driver, emaObjects.WorkflowsDD, "Visibility", 15);
				}
				catch(Exception e) {

				}
				try {
					utility.PomFindClick(driver, emaObjects.NoButton);
					logger.info("No button clicked");
					utility.checkPageIsReady(driver);
					Thread.sleep(3000);
				}catch(Exception e) {}
				try {
				Boolean isDisplay=emaObjects.WorkflowsDD.isDisplayed();
				//Assert.assertTrue(isDisplay, "Login failed");
				logger.info("Login successfully");
				}catch(Exception e) {
					excelfile.setCellData(sheetName,"Status",rowNumber,"Login Failed");
					Assert.fail("Login Failed");
				}
				Shortname=shortname;
				previousClient=currentClient;
				logger.info(previousClient);
			}
			else {
				logger.info("Client is same");
			}
			Thread.sleep(3000);
			try {
				utility.PomFindHover(driver, emaObjects.WorkflowsDD);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				utility.Pause(driver, emaObjects.MyHealthPlans, "Visibility", 15);
			}
			catch(Exception e) {

			}
			utility.PomFindMove(driver, emaObjects.MyHealthPlans);
			logger.info("My health plan clicked");

			Thread.sleep(4000);

			try {
				utility.Pause(driver, emaObjects.plan1199DEIU, "Visibility", 15);
			}
			catch(Exception e) {

			}
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", emaObjects.plan1199DEIU);
			logger.info("1199DEIU plan clicked");
			utility.checkPageIsReady(driver);
			Thread.sleep(3000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0)");

			try {
				utility.Pause(driver, emaObjects.ClaimStatusInquiry, "Visibility", 15);
			}
			catch(Exception e) {

			}
			emaObjects.ClaimStatusInquiry.click();
			logger.info("Claim Status Inquiry clicked");

			try {
				utility.Pause(driver, emaObjects.BillingEntityField, "Visibility", 15);
			}
			catch(Exception e) {

			}
			utility.PomFindClick(driver, emaObjects.BillingEntityField);
			Thread.sleep(2000);
			String [] splitClient= clientName.split(" ");
			logger.info(splitClient[0]);
			utility.PomFindField(driver, emaObjects.BillingEntityField, splitClient[0] + " " + splitClient[1]);
			Thread.sleep(3000);
			utility.PomFindField(driver, emaObjects.BillingEntityField, Keys.ARROW_DOWN);
			Thread.sleep(3000);
			utility.PomFindField(driver, emaObjects.BillingEntityField, Keys.ENTER);
			logger.info("Billing entity entered");
			Thread.sleep(4000);
			utility.PomFindField(driver, emaObjects.MemberIdField, patID);
			logger.info("Value entered in member id field");
			utility.PomFindField(driver, emaObjects.DOBField, dob);
			logger.info("Value entered in DOB field");
			String name[]=patient.split(",");
			patientFirstName= name[1].strip();
			patientLastname= name[0].strip();
			utility.PomFindField(driver, emaObjects.FirstNameField, patientFirstName);
			logger.info("Value entered in first name field");
			utility.PomFindField(driver, emaObjects.LastNameField, patientLastname);
			logger.info("Value entered in last name field");
			utility.PomFindClear(driver, emaObjects.DOSStartField);
			utility.PomFindField(driver, emaObjects.DOSStartField, dos);
			logger.info("Value entered in DOS starting date field");
			utility.PomFindClear(driver, emaObjects.DOSEndField);
			utility.PomFindField(driver, emaObjects.DOSEndField, dos);
			logger.info("Value entered in DOS ending date field");
			utility.PomFindClick(driver, emaObjects.SearchButton);
			logger.info("Search button clicked");
			
			try {
				utility.Pause(driver, emaObjects.noRecordFoundText, "Visibility", 15);
			}
			catch(Exception e) {

			}
			try {
				
				text=utility.PomReturnText(driver, emaObjects.noRecordFoundText);
				excelfile.setCellData(sheetName,"Status",rowNumber,"No record found");
				if(text.contains("No record"))
				{
					excelfile.setCellData(sheetName,"Status",rowNumber,"No record found");
					Assert.fail("No record found");
				}
				
			}
			catch(Exception ex)
			{
				
			}
			
try {
				int s= emaObjects.SearchResults.size();
				utility.PomFindClick(driver, emaObjects.SearchResults.get(s-1));
				logger.info("Search result clicked");
				Thread.sleep(3000);
			}
			catch(Exception ex)
			{
				
			}
			Thread.sleep(5000);
			try {
				utility.Pause(driver, emaObjects.PrintButton, "Visibility", 15);
			}
			catch(Exception e) {

			}
			Robot robot=new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(4000);
			if(bit2)
			{
				Thread.sleep(4000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_ENTER);	
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyPress(KeyEvent.VK_DOWN);	
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_ENTER);	
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				Thread.sleep(4000);
				robot.keyPress(KeyEvent.VK_ENTER);	
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				bit2=false;
			}
			else {
				Thread.sleep(4000);
				robot.keyPress(KeyEvent.VK_ENTER);	
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}


			Thread.sleep(4000);
			try {
				robot.keyPress(KeyEvent.VK_BACK_SPACE);	
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			}
			catch(Exception ex)
			{

			}
			robot.keyPress(KeyEvent.VK_C);	
			robot.keyRelease(KeyEvent.VK_C);
			robot.keyPress(KeyEvent.VK_SHIFT);  
			robot.keyPress(KeyEvent.VK_SEMICOLON);  
			robot.keyRelease(KeyEvent.VK_SEMICOLON);  
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_BACK_SLASH);	
			robot.keyRelease(KeyEvent.VK_BACK_SLASH);
			robot.keyPress(KeyEvent.VK_P);	
			robot.keyRelease(KeyEvent.VK_P);
			robot.keyPress(KeyEvent.VK_BACK_SLASH);	
			robot.keyRelease(KeyEvent.VK_BACK_SLASH);
			robot.keyPress(KeyEvent.VK_D);	
			robot.keyRelease(KeyEvent.VK_D);

			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			String filePath=System.getProperty("user.dir");
			File theNewestFile = null;


			//get the current directory
			Thread.sleep(3000);

			File trgDir = new File(System.getProperty("user.dir"));
			File srcDir = new File("C:\\p");

			FileUtils.copyDirectory(srcDir, trgDir);
			logger.info("File copied");
			File srcDir1 = new File("C:\\p\\d.pdf");
			if(srcDir1.delete())
			{
				System.out.println("File deleted");
			}
			else {
				System.out.println("File not deleted");
			}

			//get the current directory
			File currentDirectory = new File(System.getProperty("user.dir"));
			LastModifiedFileComparator comparator = new LastModifiedFileComparator();

			//get the last modified date (by date)
			File[] sortedFiles = comparator.sort(currentDirectory.listFiles((FileFilter)FileFileFilter.FILE));

			int size=sortedFiles.length;
			File file=sortedFiles[size-1];   
			System.out.println("Recently downloaded file:---"+file.getName());
			file=new File(file.getName());
			if(file.getName().contains("d.pdf"))
			{
				dateFormat=utility.dateformatForCureBilling(dos);
				newFileName=Shortname+"-"+patient+"-"+dateFormat+".pdf";
				newfile=new File(newFileName);
				file.renameTo(newfile);
				System.out.println("Recently downloaded file renamed to:---"+newfile.getName());
				logger.info(rowNumber);
				excelfile.setCellData(sheetName,"Status",rowNumber,"Executed successfully");
			}
			else {
				excelfile.setCellData(sheetName,"Status",rowNumber,"file not downloaded");
				Assert.fail("file not downloaded");
			}
			
		
		}
	}


	// It will execute after every test execution

	@AfterMethod
	public void onTestFaliure(ITestResult iTestResult) {

		if (ITestResult.FAILURE == iTestResult.getStatus()) {

			utility.captureScreenshot(driver, iTestResult.getName());

			if (driver instanceof WebDriver) {
				System.out.println("Screenshot Captured for test case:" + tlistener.getTestMethodName(iTestResult));
				tlistener.saveScreenshotPNG(driver);
			}

		}

	}



	// DATA INSERTION
	@org.testng.annotations.DataProvider
	public Object[][] get_print_data() {
		String filepath = System.getProperty("user.dir") + "\\1199 follow up 01.20.2020.xlsx";
		// String filepath = System.getProperty("user.dir")+"\\TestExcelFile.xlsx";
		if (excelfile == null) {
			// load the Excel sheet
			excelfile = new Xls_Reader(filepath);
		}
		String sheetName = "Sheet1";
		int rows = excelfile.getRowCount(sheetName); // Get Row Count
		int cols = excelfile.getColumnCount(sheetName); // Get Column Count

		Object data[][] = new Object[rows - 1][cols]; // -1
		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excelfile.getCellData(sheetName, colNum, rowNum);// -2
			}
		}
		return data;
	}

}