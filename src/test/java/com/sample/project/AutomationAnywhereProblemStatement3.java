package com.sample.project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationAnywhereProblemStatement3 {

	WebDriver driver;
	String url = "https://www.automationanywhere.com/";
	WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		// Launch chrome browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test (timeOut = 3000, priority = 0, description = "Launch Chrome browser & verify logo")
	public void testLogo() {
		// Go to automation anywhere url
		driver.get(url);
		// Find logo webelement using xpath
		WebElement logo = driver.findElement(By.xpath("//a[contains(@class, 'header-logo')]/img"));
		// Assert logo is displayed
		if (logo.isDisplayed() == true) {
			assertEquals(logo.getAttribute("alt"), "Automation Anywhere");
		} else
			System.out.println("Logo is not displayed");
	}

	@Test (priority = 1, enabled = false, description = "verify request demo button")
	public void testRequestDemoButton() {
		// Remove accept cookies pop up
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-banner-sdk")));
			// accept cookies
		WebElement accept_cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
		if (accept_cookies.isDisplayed()) {
			accept_cookies.click();			
		}
		// Find request demo webelement using xpath
		WebElement request_demo = driver.findElement(By.xpath("//div[@class = 'banner-cta-box']/a[@title='Request demo']"));
		// Assert request demo button is present & clickable
		if (request_demo.isDisplayed() == true) {
			if (request_demo.isEnabled() == true) {
				assertEquals(request_demo.getAttribute("title"), "Request demo");
			}
		} else
			System.out.println("Request Demo button is not present");

	}

	@AfterClass
	public void tearDown() {
		// quit browser
		driver.quit();
	}
}
