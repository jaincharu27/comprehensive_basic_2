package com.sample.project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightSearch_problem_2_3_4 {

	WebDriver driver;
	String url = "https://www.makemytrip.com/";
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		// Launch chrome browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test (description = "Launch Chrome browser & verify flight search")
	public void testFilghtSearch() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Go to make my trip url
		driver.get(url);
		//Wait till login model displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("commonModal__close")));
		// Close login model
		driver.findElement(By.className("commonModal__close")).click();
		// Click on flights menu
		driver.findElement(By.className("menu_Flights")).click();
		// Click on round trip
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Round Trip']")));
		driver.findElement(By.xpath("//li[text()='Round Trip']")).click();
		// Enter from city
		driver.findElement(By.id("fromCity")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='From']")));
		driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("HYD");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Rajiv Gandhi International Airport']")));
		driver.findElement(By.xpath("//p[text()='Rajiv Gandhi International Airport']")).click();
		//Enter to city
		driver.findElement(By.id("toCity")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='To']")));
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("MAA");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Chennai International Airport']")));
		driver.findElement(By.xpath("//p[text()='Chennai International Airport']")).click();
		// Click departure date and select date
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Departure']")));
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		driver.findElement(By.className("DayPicker-Day--today")).click();
		// select return date
		driver.findElement(By.xpath("(//div[@class='dateInnerCell']/p[text()='1'])[3]")).click();
		// Click on search button
		driver.findElement(By.xpath("//p[@data-cy='submit']")).click();
		
		//Wait till search page displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OKAY, GOT IT!']")));
		// Click on updated price pop up box
		driver.findElement(By.xpath("//button[text()='OKAY, GOT IT!']")).click();
		// Verify label for filght search from HYD to Chennai
		String title = driver.findElement(By.xpath("//div[@class='listingRhs']/p")).getText();
		System.out.println(title);
		if (title.equalsIgnoreCase("Flights from Hyderabad to Chennai, and back")) {
			Assert.assertTrue(true);
		} else
			System.out.println("Search page is not found");
			Assert.assertTrue(false);
	}

	@AfterMethod
	public void tearDown() {
		// quit browser
		//driver.quit();
	}
}
