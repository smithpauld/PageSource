
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestNG {

	public WebDriver driver;
	public WebElement searchbox;
	public WebElement searchbutton;
	public WebElement linktext;

	// locate id of google searchbox
	@Test
	public void searchbox() {

		searchbox = driver.findElement(By.id("lst-ib"));

		// enter "selenium" in the searchbox
		searchbox.sendKeys("Selenium");

		// Find search button ID
		searchbutton = driver.findElement(By.name("btnK"));

		// select search button
		searchbutton.submit();

		// Wait for link

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rso']/div[1]/div/div[1]/div/h3/a")));

		// Find link for selenium
		linktext = driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div[1]/div/h3/a"));

		// Select Selenium link
		linktext.click();

		// Select Download tab on Selenium page

		driver.findElement(By.cssSelector("#menu_download > a")).click();

		// Select Javadoc ink

		driver.findElement(
				By.cssSelector("#mainContent > table:nth-child(13) > tbody > tr:nth-child(1) > td:nth-child(6) > a"))
				.click();

		// Switch to frame containing Selenium package
		driver.switchTo().frame("packageListFrame");

		// Select Selenium package
		driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a")).click();

		// switch to default frame

		driver.switchTo().defaultContent();

		// Switch to frame containing webdriver
		driver.switchTo().frame("packageFrame");

		// Select Webdriver interface
		driver.findElement(By.cssSelector("body > div > ul:nth-child(2) > li:nth-child(10) > a > span")).click();

		// take a screenshot
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			// now copy the screenshot to desired location using copyFile method
			// this will create a different file name each time using the
			// currentTime
			FileUtils.copyFile(srcFile,
					new File("C:\\Selenium Screenshots\\" + System.currentTimeMillis() + " testing.png"));

		}

		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(srcFile.getAbsolutePath());

		// switch to default frame

		driver.switchTo().defaultContent();

		// view the page source and save to a file

		String pagesourcevalue = driver.getPageSource();

		try {
			File f = new File("c:\\Selenium Screenshots\\PageSource" + System.currentTimeMillis() + ".txt");
			FileWriter writer = new FileWriter(f);
			writer.write(pagesourcevalue);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver",
				"U:\\git\\automation-testing\\resources\\webDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://www.google.com");

		driver.manage().window().maximize();

	}

	@AfterTest
	public void afterTest() {

		System.out.println(" I am done");
		driver.close();
	}
}
