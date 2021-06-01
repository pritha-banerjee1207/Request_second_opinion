package Reusable_Functions;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;


public class Generic_function {
	public static WebDriver driver;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell cell,f;
	public static XSSFRow row;
	public static String CellData,path;
	static File file = new File("configuration/config.properties");
	static Properties prop = new Properties();
	public static int iter; 
	public static String mainwindow ;
	public static int col;
	public static Set<String> s1 ;
	public static Iterator<String> i1 ;
	static int size;
	public static boolean value1;
	static List<WebElement> grid_elements,drp_list;
	static List<WebElement> grid_element;
	static WebElement ele;
	static String ere,ptr,value;
	public static WebElement val,element;
	
	/* Browser launching using driver that is specified in the config.properties file , navigating to Landing Welcome Page and returning driver object*/
	public static void Browser_Launch() throws IOException {
		FileInputStream fileInput;
		fileInput = new FileInputStream(file);
		prop.load(fileInput);
		System.setProperty("webdriver.chrome.driver",getDriverPath());
		Map<String, Object> prefs = new HashMap<String, Object>();
		//Pass the argument 1 to allow and 2 to block
		prefs.put("profile.default_content_setting_values.notifications", 1);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.navigate().to(getURL());	
		driver.manage().window().maximize();
		click("broweser_launch_advance");
		click("broweser_launch_proceed");
	}
	
	/* Browser launch*/
	public WebDriver browser_launch() throws IOException {
		FileInputStream fileInput;
		fileInput = new FileInputStream(file);
		prop.load(fileInput);
		 System.setProperty("webdriver.chrome.driver",getDriverPath());
		 driver =new ChromeDriver();
		 driver.navigate().to(getURL());
		 driver.manage().window().maximize();
		 click("broweser_launch_advance");
			click("broweser_launch_proceed");
			((JavascriptExecutor) driver).executeScript("window.open()");
		    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(1));
		    driver.get("https://www.medicare.gov/account/login/");
		    
		    driver.switchTo().window(tabs.get(0));
		return driver;
	}

	/*To get test data iteration value from config.properties file*/
	public static int Dataiter() {            
		iter=Integer.parseInt(prop.getProperty("Data_iteration"));
		return iter;		
	}	

	/* Reading chrome driver path from config.properties file */
	public static String getDriverPath() {
		String driverpath= prop.getProperty("Driverpath");
		if(driverpath!=null) return driverpath ;
		else throw new RuntimeException ("Driverpath is not specified in the Config.properties");
	}

	/* Reading URL from config.properties file */
	public static String getURL() {
		String URL= prop.getProperty("URL");
		if(URL!=null) return URL ;
		else throw new RuntimeException ("URL is not specified in the Config.properties");
	}

	/* Reading Excel file path  from config.properties   */
	public static String getFilepath() {
		String filepath= prop.getProperty("Filepath");
		if(filepath!=null) return filepath ;
		else throw new RuntimeException ("Filepath is not specified in the Config.properties");
	}

	/*To get directory path of screenshots folder*/

	public static String getDir() {
		String dirpath= prop.getProperty("Dirpath");
		if(dirpath!=null) return dirpath ;
		else throw new RuntimeException ("user Dir is not specified in the Config.properties");
	}

	/*  Taking Screenshot of failed test cases  */
	public static  void takeScreenShot(String fileName) throws IOException {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(getDir()+fileName+".png"));
	}

	/* Click operation for a particular fieldname that is passing to this function through finding locator value of fieldname using OR_reader function*/
	public static void click(String fieldname) throws IOException {
		driver.findElement(By.xpath(OR_reader("Object_Locator", fieldname))).click();
	}

	/* Refresh function to refresh the current URL opened in browser */
	public static void browser_refresh() {
		driver.navigate().refresh();
	}

	/* To wait the browser till the time passed to this function */
	public static void browser_wait(int time) {
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
	}

	/*Function to clear the value in a particular field*/
	public static void field_clear(String fieldname) throws IOException {
		val = driver.findElement(By.xpath(OR_reader("Object_Locator", fieldname)));
		val.clear();
	}

	/* To find object locator value of a particular fieldname passing to this function by loading Excel workbook*/
	public static  String OR_reader(String sheetname,String Fieldname) throws IOException  {
		File src=new File(getFilepath());
		FileInputStream finput;
		finput = new FileInputStream(src);
		workbook = new XSSFWorkbook(finput);
		sheet = workbook.getSheet(sheetname);
		int rowCount = sheet.getPhysicalNumberOfRows();
		row = sheet.getRow(0);
		for(int i=1;i<rowCount;i++) {
			cell = sheet.getRow(i).getCell(0);
			CellData = cell.getStringCellValue();
			if(CellData.equals(Fieldname))
			{
				f= sheet.getRow(i).getCell(2);
				path = f.getStringCellValue();
				break;
			}
			else
			{
				continue;
			}
		}
		return path;
	}

	/* To read test data value of a particular fieldname passing to this function using findRow function to get row number from excel sheet  */
	public static String td_reader(String fieldname) {
		sheet = workbook.getSheetAt(0);
		col= Dataiter();
		String td_value=sheet.getRow(findRow(fieldname)).getCell(col).getStringCellValue();
		return td_value;
	}

	/* To read test data value of a particular fieldname using index  where its values are seperated with a comma within cell in excel sheet  */
	public static String td_reader(String fieldname,int index){
		sheet = workbook.getSheetAt(0);
		col= Dataiter();
		String td_value = sheet.getRow(findRow(fieldname)).getCell(col).getStringCellValue();
		String[] str = td_value.split(",");
		return str[index];
	}

	/* To get row number of a particular fieldname passing to this function from excel sheet  */
	public static int findRow(String fieldname) {
		sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getRichStringCellValue().getString().trim().equals(fieldname)) {
					return row.getRowNum();  
				}
			}
		}       
		return 0;
	}

	/*To switch the browser*/
	public static void browser_switch() {
		driver.switchTo().window(mainwindow);
	}

	/*To close the browser */
	public static void browser_close() {
		driver.close();
	}

	/*To quit the browser */ 
	public static void driverquit() {
		driver.quit();	
	}

	/*To go back to the browser */
	public static void browser_back() {
		driver.navigate().back();
	}

	/* Function used to handle multiple window*/
	public static void browser_handle() {
		mainwindow = driver.getWindowHandle();
		s1 = driver.getWindowHandles();
		i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
			}
		}    
	}

	/* To click multiple tabs inside home page*/
	public static void grid_tiles(String grid_path) throws IOException, InterruptedException {
		size = driver.findElements(By.xpath(grid_path)).size();
		try {
			for (int i=0; i<size;i++)
			{	
				grid_elements =driver.findElements(By.xpath(grid_path));	
				ele = grid_elements.get(i);
				ere= ele.getText();
				if(ere.equalsIgnoreCase("Wallet")) {
					ele.click();
					value1 = driver.findElement(By.xpath(OR_reader("Object_Locator", "wallet_page_title"))).isDisplayed();
					Assert.assertEquals(true,value1);	
					browser_wait(5);
					browser_back();
				}else if(ere.equalsIgnoreCase("Second Opinions"))
				{
					ele.click();
					value1 = driver.findElement(By.xpath(OR_reader("Object_Locator", "second_opinion"))).isDisplayed();
					Assert.assertEquals(true,value1);	
					browser_wait(5);
					browser_back();
				}
				else {
					ele.click();
					value = driver.findElement(By.xpath(OR_reader("Object_Locator", ere))).getText();
					value1 = driver.findElement(By.xpath(OR_reader("Object_Locator", ere))).isDisplayed();
					Assert.assertEquals(true,ere.equalsIgnoreCase(value));
					Assert.assertEquals(true,value1);	
					browser_wait(5);
					browser_back();	
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		browser_wait(5);
	}

	/* To click multiple tabs inside services page*/
	public static void grid_tile(String grid_path) throws IOException, InterruptedException {
		size = driver.findElements(By.xpath(grid_path)).size();

		for (int i=0; i<size;i++)
		{	
			grid_elements =driver.findElements(By.xpath(grid_path));	
			ele = grid_elements.get(i);
			ere= ele.getText();

			if(ere.equalsIgnoreCase("Second Opinions")) {
				ele.click();
				value1 = driver.findElement(By.xpath(OR_reader("Object_Locator", "second_opinion"))).isDisplayed();
				Assert.assertEquals(true,value1);	
				browser_wait(10);
				click("services");
			}else if(ere.equalsIgnoreCase("Bills"))
			{
				ele.click();
				browser_wait(10);
				click("services");
			}
			else {
				try {
					if (ptr.equalsIgnoreCase(ere)) {
						value = driver.findElement(By.xpath(OR_reader("Object_Locator", ere))).getText();
						Assert.assertEquals(true,value);

					}
				}catch(Exception e) {
					ele.click();
					browser_wait(10);
					takeScreenShot(ere);
					click("ok");			
				}

			}
		}

	}

	/* To click multiple tabs inside utilities page*/
	public static void utilities_grid_tile(String grid_path) throws IOException, InterruptedException {
		size = driver.findElements(By.xpath(grid_path)).size();
		for (int i=0; i<size;i++)
		{	
			grid_elements =driver.findElements(By.xpath(grid_path));	
			ele = grid_elements.get(i);
			ere= ele.getText();
			if(ere.equalsIgnoreCase("Wallet")) {
				ele.click();
				value1 = driver.findElement(By.xpath(OR_reader("Object_Locator", "utilities_add_card"))).isDisplayed();
				Assert.assertEquals(true,value1);	
				browser_wait(20);
				click("utilities");
			}else if(ere.equalsIgnoreCase("Award Points"))
			{
				ele.click();
				browser_wait(20);
				value1 = driver.findElement(By.xpath(OR_reader("Object_Locator", "award_points_title"))).isDisplayed();
				Assert.assertEquals(true,value1);
				browser_back();
			}
			else {
				try {
					if (ptr.equalsIgnoreCase(ere)) {
						value = driver.findElement(By.xpath(OR_reader("Object_Locator", ere))).getText();
						Assert.assertEquals(true,value);
					}
				}catch(Exception e) {
					ele.click();
					Thread.sleep(2000);
					takeScreenShot(ere);
					click("ok");
				}
			}
		}
	}

	/* To select value passed as text from a dropdown menu*/
	public static void drop_down(String drp_click,String text) throws IOException {
		drp_list = driver.findElements(By.xpath(drp_click));
		int size= drp_list.size();	
		for( int i=0; i<size;i++){
			ele = drp_list.get(i);
			ere= ele.getText();
			if(ere.equalsIgnoreCase(text)) {
				ele.click();
			}

		}
	}
	
	public static void click_javascript(String fieldname) throws IOException {
		element= driver.findElement(By.xpath(OR_reader("Object_Locator", fieldname)));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
}
	
