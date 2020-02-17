package test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestLogin {
	
	@Test
	public void testSuccessfullLogin() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/LIVEtheMUSIC/");
		
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("testusername1");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("testusername1");
		
		driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
		
		WebElement welcomeText = driver.findElement(By.xpath("/html/body/div/div[2]/div/h2/i"));
		
		String value = welcomeText.getText();
		
		assertEquals("Welcome testusername1", value);
	}
}
