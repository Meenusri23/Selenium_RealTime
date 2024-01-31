package Login_TestCases;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Login {

	String Data[][]= null;
	WebDriver driver;

	/*
	 * Data Provider Annotation code
	 * String Data[][]={ {"student","Password123"}, {"student1","Password12"},
	 * {"student","Password12"}, {"student1","Password123"} };
	 */
	@DataProvider(name="Login Data")
	public String[][] LoginDataProvider() throws BiffException, IOException
	{
		Data=getExcelData();
		return Data;
	}

	public String[][] getExcelData() throws BiffException, IOException
	{
		FileInputStream excel = new FileInputStream("D:\\UserCredentials.xls");

		Workbook workbook = Workbook.getWorkbook(excel);

		Sheet sheet = workbook.getSheet(0);

		int RowCount = sheet.getRows();

		int ColumnCount = sheet.getColumns();

		String testData[][] = new String[RowCount-1][ColumnCount];

		for(int i=1;i<RowCount;i++)
		{
			for(int j=0;j<ColumnCount;j++)
			{
				testData[i-1][j]= sheet.getCell(j, i).getContents();

			}
		}
		return testData;
	}

	@BeforeTest
	public void OpenBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

	}


	@Test(dataProvider = "Login Data")
	public void loginwithCorrectCredentials(String Uname, String Pwd)
	{
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\DRIVERS\\chromedriver.exe");
		 * 
		 * WebDriver driver = new ChromeDriver();
		 * 
		 * driver.manage().window().maximize();
		 */

		driver.get("https://practicetestautomation.com/practice-test-login/");

		//Enter UserName

		WebElement UserName = driver.findElement(By.id("username"));

		UserName.sendKeys("Uname");

		//Enter Password

		WebElement Password = driver.findElement(By.name("password"));
		Password.sendKeys("Pwd");

		//Clicking on Login button
		WebElement LoginBtn = driver.findElement(By.id("submit"));
		LoginBtn.click();
		/* 
		 * //Close browser driver.quit();
		 */
	}
	@AfterTest
	public void CloseBrowser()

	{
		//Close browser
		driver.quit();
	}

}
