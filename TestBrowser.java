package Wiley;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;

import junit.framework.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBrowser {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.gecko.driver", "E:\\Java Projects\\Addons\\geckodriver.exe");
		
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.BROWSER, Level.INFO);
		logs.enable(LogType.CLIENT, Level.INFO);
		logs.enable(LogType.DRIVER, Level.INFO);
		logs.enable(LogType.PERFORMANCE, Level.INFO);
		logs.enable(LogType.SERVER, Level.ALL);

		DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
		desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		
		WebDriver driver = new FirefoxDriver(desiredCapabilities);
		
//		Check 01
		driver.get("http://www.wiley.com/WileyCDA/");
		Thread.sleep(5000);
		try
		{
			driver.findElement(By.linkText("Home")).isDisplayed();
			driver.findElement(By.linkText("Subjects")).isDisplayed();
			driver.findElement(By.linkText("About Wiley")).isDisplayed();
			driver.findElement(By.linkText("Contact Us")).isDisplayed();
			driver.findElement(By.linkText("Help")).isDisplayed();
			System.out.println("---------\nCheck 01: PASSED. All links are on page\n---------");
		}
		catch (NoSuchElementException e)
		{
			e.getCause();
			System.err.println("Failed Cause - " + e);
			System.err.println("---------\nCheck 01: FAILED. Some links are not on page\n---------");
		}
		
//		Check 02
		Thread.sleep(1000);
		WebElement options2 = driver.findElement(By.xpath("//div[@id='homepage-links']"));
		String ActualStr = options2.getText().replace("\n", "");
		String ExpectedStr = "StudentsAuthorsInstructorsLibrariansSocietiesConferencesBooksellersCorporationsInstitutions";
		try
		{
			Assert.assertEquals(ExpectedStr, ActualStr);
			System.out.println("Check 02: PASSED. Items under Resources sub-header are equals\n---------");
		}
		catch (ComparisonFailure e)
		{
			e.getCause();
			System.err.println("Failed Cause - " + e);
			System.err.println("Check 02: FAILED. Items under Resources sub-header are not equals\n---------");
		}
		
//		Check 03
		Thread.sleep(1000);
		try
		{
			WebElement options3 = driver.findElement(By.linkText("Students"));
			options3.click();
		}
		catch (NoSuchElementException e)
		{
			e.getCause();
			System.err.println("Failed Cause - " + e);
			System.err.println("Check 03: FAILED. Link is not on page\n---------");
			driver.close();
			driver.quit();
		}
		
		Thread.sleep(5000);
		if (driver.getCurrentUrl().contains("WileyCDA/Section/id-404702.html"))
			System.out.println("Url page - " + driver.getCurrentUrl() + "\n is equal");
		else
			System.err.println("Url page - " + driver.getCurrentUrl() + "\n is not equal");
		System.out.println("Page title is - " + driver.getTitle());
		if (driver.getTitle().contains("Wiley: Students"))
			{
				System.out.println("Title page - " + driver.getTitle() + "\n is equal");
				System.out.println("Check 03: PASSED. Students link and Students title are displayed\n---------");
			}
		else
			{
				System.err.println("Title page - " + driver.getTitle() + "\n is not equal");
				System.err.println("Check 03: FAILED. Students link and Students title are not displayed\n---------");
			}
				
//		Check 04
		Thread.sleep(1000);
		WebElement options4 = driver.findElement(By.xpath("//ul[@class='autonavLevel1']"));
		ActualStr = options4.getText().replace("\n", "");
		if (ActualStr.contains("Authors") && ActualStr.contains("Librarians") && ActualStr.contains("Booksellers") && ActualStr.contains("Instructors") && ActualStr.contains("Students") && ActualStr.contains("Societies") && ActualStr.contains("Corporate Partners"))
			{
				System.out.println("Authors, Librarians, Booksellers, Instructors, Students, Societies and Corporate Partners are on page in 'Resources For'");
				System.out.println("Check 04: PASSED. Items under Resources For are equal\n---------");
			}
		else
			System.err.println("Check 04: FAILED. Items under Resources For are not equal\n---------");
		
//		Check 05
		WebElement options5 = driver.findElement(By.xpath("//li[@class='active autonavItem parent']")).findElement(By.tagName("span"));
		WebElement options6 = driver.findElement(By.linkText("Corporate Partners"));
		if(options5.getCssValue("color").equals(options6.getCssValue("color")))
			System.err.println("No. Color for Students is same others autonavItem");
		else
			System.out.println("Yes. Color for Students is different others autonavItem");
		Thread.sleep(1000);
		options5.click();
		Thread.sleep(2000);
		if (driver.getTitle().contains("Wiley: Students"))
			{
				System.out.println("Page title is - " + driver.getTitle() + "\nStudents item is not clickable");
				System.out.println("Check 05: PASSED. Students item is selected\n---------");
			}
		else
			{
				System.err.println("Page title is - " + driver.getTitle() + "\nStudents item is clickable");
				System.err.println("Check 05: FAILED. Students item is not selected\n---------");
			}
		
//		Check 06
		Thread.sleep(1000);
		try
		{
			WebElement options7 = driver.findElement(By.linkText("Home"));
			options7.click();
			Thread.sleep(2000);
			System.out.println("Page title is - " + driver.getTitle() + "\nCheck 06: PASSED. Home link is opened\n---------");
		}
		catch (NoSuchElementException e)
		{
			e.getCause();
			System.err.println("Failed Cause - " + e);
			System.err.println("Check 06: FAILED. Link is not on page\n---------");
			driver.close();
			driver.quit();
		}
		
//		Check 07
		Thread.sleep(1000);
		WebElement options8 = driver.findElement(By.xpath("//input[@id='EmailAddress' and @name='EmailAddress']"));
		options8.click();
		options8 = driver.findElement(By.xpath("//button[@id='id31' and @value='Subscribe']"));
		options8.click();
		Thread.sleep(1000);
		try
		{
			Thread.sleep(1000);
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert is present");
			String messageAllert = alert.getText();
			if (messageAllert.contains("Please enter email address"))
				System.out.println("Text in Alert is OK - " + messageAllert);
			else
				System.out.println("Text in Alert is not OK - " + messageAllert);
			alert.accept();
			System.out.println("Check 07: PASSED. Alert is appeared and text is OK\n---------");
		}
		catch (NoAlertPresentException e)
		{
			e.getCause();
			System.err.println("Alert is not present");
			System.err.println("Check 07: FAILED. Alert is not appeared\n---------");
			driver.close();
			driver.quit();
		}
/*		
//		Check 08
		Thread.sleep(10000);
		WebElement options81 = driver.findElement(By.xpath("//input[@id='EmailAddress' and @name='EmailAddress']"));
		options81.sendKeys("emailgmail.com");
		Thread.sleep(10000);
		options81 = driver.findElement(By.xpath("//button[@id='id31' and @value='Subscribe']"));
		options81.click();
		try
		{
			Thread.sleep(1000);
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert is present");
			String messageAllert = alert.getText();
			if (messageAllert.contains("Invalid email address."))
				System.out.println("Text in Alert is OK - " + messageAllert);
			else
				System.out.println("Text in Alert is not OK - " + messageAllert);
			alert.accept();
			System.out.println("Check 08: PASSED. Alert is appeared and text is OK\n---------");
		}
		catch (NoAlertPresentException e)
		{
			System.err.println("Alert is not present");
			System.err.println("Check 08: FAILED. Alert is not appeared\n---------");
		}
*/
		
//		Check 09
		Thread.sleep(2000);
		WebElement options9 = driver.findElement(By.xpath("//input[@id='query' and @name='query']"));
		options9.sendKeys("for dummies");
		Thread.sleep(1000);
		options9 = driver.findElement(By.xpath("//input[@class='icon icon__search search-form-submit' and @value='submit']"));
		options9.click();
		Thread.sleep(5000);
		System.out.println("Page title is - " + driver.getTitle());
		WebElement options10 = driver.findElement(By.xpath("//div[@id='search-results']"));
		String StrElementCount = options10.getAttribute("childElementCount");
		Integer NumElementCount = 0;
		try
		{ 
			NumElementCount = new Integer(StrElementCount);
		}
		catch (NumberFormatException e)
		{
			System.err.println("Неверный формат строки!");
		}
		if (NumElementCount >= 1)
			{
				System.out.println("Search Results - List of item has " + NumElementCount + " item(s)");
				System.out.println("Check 09: PASSED. List of items are appeared\n---------");
			}
		else
			{
				System.err.println("Search Results - List of item are invisible!");
				System.err.println("Check 09: FAILED. List of items are not appeared\n---------");
			}
				
//		Check 10
		Thread.sleep(2000);
		Integer RandomN = new Random(System.currentTimeMillis()).nextInt(NumElementCount);
		System.out.println("Random number = " + RandomN);
		List<WebElement> options11 = driver.findElements(By.xpath("//div[@class='product-title']"));
		Integer fori;
		String[] str = new String[options11.size()];
		for(fori=0; fori<options11.size(); fori++)
			{
			str[fori] = options11.get(fori).getAttribute("innerText");
			}
		Thread.sleep(1000);
		System.out.println("Random Link is - " + str[RandomN]);
		try
		{
			WebElement options12 = driver.findElement(By.linkText(str[RandomN]));
			options12.click();
			Thread.sleep(5000);
		}
		catch (NoSuchElementException e)
		{
			e.getCause();
			System.err.println("Failed Cause - " + e);
			System.err.println("Check 10: FAILED. Link is not on page\n---------");
			driver.close();
			driver.quit();
		}
		if (driver.getTitle().contains(str[RandomN]))
			{
				System.out.println("Page title is - '" + driver.getTitle() + "'\n'" + str[RandomN] + "'\n is equal the title");
				System.out.println("Check 10: PASSED. Random linked book is opened with equal header\n---------");
			}
		else
			{
				System.err.println("Page title is - '" + driver.getTitle() + "'\n'" + str[RandomN] + "'\n is not equal the title");
				System.err.println("Check 10: FAILED. Random linked book is opened with not equal header\n---------");
			}
		
//		Check 11
		Thread.sleep(3000);
		try
		{
			WebElement options13 = driver.findElement(By.linkText("Home"));
			options13.click();
			Thread.sleep(3000);
			System.out.println("Page title is - " + driver.getTitle() + "\nCheck 11: PASSED. Home link is opened\n---------");
		}
		catch (NoSuchElementException e)
		{
			e.getCause();
			System.err.println("Failed Cause - " + e);
			System.err.println("Check 11: FAILED. Link is not on page\n---------");
			driver.close();
			driver.quit();
		}
		
//		Check 12
		Thread.sleep(3000);
		final Set<String> oldWindowsSet = driver.getWindowHandles();
		try
		{
			WebElement options14 = driver.findElement(By.linkText("Institutions"));
			options14.click();
		}
		catch (NoSuchElementException e)
		{
			e.getCause();
			System.err.println("Failed Cause - " + e);
			System.err.println("Check 12: FAILED. Link is not on page\n---------");
			driver.close();
			driver.quit();
		}
		Thread.sleep(10000);
		Set<String> newWindowsSet = driver.getWindowHandles();
		newWindowsSet.removeAll(oldWindowsSet);
		String newWindow = newWindowsSet.iterator().next();
		driver.switchTo().window(newWindow);
		System.out.println("Page title is - " + driver.getTitle());
		if (driver.getCurrentUrl().contains("edservices.wiley.com"))
			{
				System.out.println("Url page - " + driver.getCurrentUrl() + "\n is equal");
				System.out.println("Check 12: PASSED. Institutions link is opened in new window (or tab)\n---------");
			}
		else
			{
				System.err.println("Url page - " + driver.getCurrentUrl() + "\n is not equal");
				System.err.println("Check 12: FAILED. The link is not opened\n---------");
			}
		
		Thread.sleep(5000);
		
		driver.close();
		driver.quit();
	}
	
}