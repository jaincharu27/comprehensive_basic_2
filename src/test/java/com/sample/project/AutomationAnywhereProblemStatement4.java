package com.sample.project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationAnywhereProblemStatement4 {

	WebDriver driver;
	String url = "https://www.automationanywhere.com/";
	WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		// Launch chrome browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Go to automation anywhere url
		driver.get(url);
		// Remove accept cookies pop up
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-banner-sdk")));
		// accept cookies
		WebElement accept_cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
		if (accept_cookies.isDisplayed()) {
			accept_cookies.click();
		}

	}

	@Test (description = "Verify header menu list is present on home page and onclick, it is navigating to respective page")
	public void testHeaderMenuLinks() throws InterruptedException {
		// get header link list
		List<WebElement> menu_links = new ArrayList<WebElement>();
		menu_links = driver.findElements(By.xpath("//ul[contains(@class, 'coh-ce-646fa54d')]/li/a"));
		String href_links[] = { "/rpa", "/products", "/solutions", "/resources", "/company/about-us" };

		for (int i = 0; i < menu_links.size(); i++) {
			Thread.sleep(2000);
			System.out.println(menu_links.get(i).getText());
			// Click on each header link
			menu_links.get(i).click();

			// Remove accept cookies pop up
//			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-banner-sdk")));
			// accept cookies
//			WebElement accept_cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
//			if (accept_cookies.isDisplayed()) {
//				accept_cookies.click();
//			}

			// Handle stateElement exception
			try {
				// Get attribute href
				String href = menu_links.get(i).getAttribute("href");
				// Assert correct page
				assertEquals(href, href_links[i]);
			} catch (StaleElementReferenceException e) {
				menu_links = driver.findElements(By.xpath("//ul[contains(@class, 'coh-ce-646fa54d')]/li/a"));
			}
		}
	}

	@AfterClass
	public void tearDown() {
		// quit browser
		driver.quit();
	}
}
