package com.fipkart.stepdefinition;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileValidation {
	static WebDriver driver;
	@Given("user launches flipkart application")
	public void user_launches_flipkart_application() {
		System.out.println("browser launch");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");
	    
	}

	@Given("user login by entering valid crendentials")
	public void user_login_by_entering_valid_crendentials() {
		
			WebElement findElement = driver.findElement(By.xpath("//button[text()='✕']"));
			findElement.click();
	}

	@When("user search mobile")
	public void user_search_mobile() throws Exception {
		driver.findElement(By.name("q")).sendKeys("realme", Keys.ENTER);
	
		File f=new File(".//Excel//cucutask1.xlsx");
		FileOutputStream f1=new FileOutputStream(f);
	    Workbook w=new XSSFWorkbook(); 
	    Sheet sheet = w.createSheet("bargan");
	    List<WebElement> mobilenames = driver.findElements(By.xpath("//div[contains(text(),'realme')]"));
		for(int i=1;i<mobilenames.size();i++)
		{
			WebElement text = mobilenames.get(i);
			String text2 = text.getText();
			System.out.println(text2);
			
			Row row = sheet.createRow(i); 
			Cell cell = row.createCell(0);
			cell.setCellValue(text2);
		}
		w.write(f1);
	}

	@When("user click on the mobile name")
	public void user_click_on_the_mobile_name() {
	WebElement element = driver.findElement(By.xpath("(//div[contains(text(),'realme')])[5]"));
	element.click();
	//window handling
	Set<String> windowHandles = driver.getWindowHandles();
	List<String> list=new ArrayList<String>();
	boolean addAll = list.addAll(windowHandles);
	WebDriver window = driver.switchTo().window(list.get(1));
	System.out.println(window);
	
	
	}

	@Then("user validate the mobile names")
	public void user_validate_the_mobile_names() throws Exception {
		//second window
		WebElement element2 = driver.findElement(By.xpath("//span[contains(text(),'realme')]"));
		String text1 = element2.getText();
		System.out.println("target stock:"+text1);
		//read excel
		File f=new File(".//Excel//cucutask1.xlsx");
		FileInputStream f1=new FileInputStream(f);
		Workbook w=new XSSFWorkbook(f1);
		Sheet sheet = w.getSheet("bargan");
		Row row = sheet.getRow(4);
		Cell cell = row.getCell(0);
		String stringCellValue = cell.getStringCellValue();
		System.out.println(stringCellValue);
		if(text1.equals(stringCellValue)) {
			System.out.println("pass");
		}else {
			System.out.println("fail");
		}
		
	}


}
