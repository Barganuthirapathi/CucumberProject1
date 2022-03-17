package com.fipkart.stepdefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class TelevisionValidation {
	static WebDriver driver;
	@Given("user launches flipkart webs")
	public void user_launches_flipkart_webs() {
		System.out.println("browser launch");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");
	}

	@Given("user login by entering valid crendentials in webs")
	public void user_login_by_entering_valid_crendentials_in_webs() {
        WebElement findElement = driver.findElement(By.xpath("//button[text()='✕']"));
		findElement.click();
	}
    @When("user search Television")
	public void user_search_Television() throws Exception {
    	driver.findElement(By.name("q")).sendKeys("Television", Keys.ENTER);
    	//excel write
    	File f=new File(".//Excel//Television.xlsx");
    	FileOutputStream f1=new FileOutputStream(f);
    	Workbook w=new XSSFWorkbook();
    	Sheet sheet = w.createSheet("bargan1");
    	List<WebElement> findElements = driver.findElements(By.xpath("//div[contains(text(),'TV')]"));
    	for(int i=0;i<findElements.size();i++) {
    		WebElement text3 = findElements.get(i);
    		String text5 = text3.getText();
    		System.out.println(text5);
    		
    		Row row = sheet.createRow(i);
    		Cell cell = row.createCell(0);
    		cell.setCellValue(text5);
    	    
    	}
    	w.write(f1);
    }

	@When("user click on the Television namee")
	public void user_click_on_the_Television_namee() throws Exception {
		WebElement findElement1 = driver.findElement(By.xpath("(//div[contains(text(),'TV')])[2]"));
		findElement1.click();
		//windows handling
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list=new ArrayList<String>();
		boolean addAll = list.addAll(windowHandles);
		WebDriver window = driver.switchTo().window(list.get(1));
		System.out.println(window);
	}

	@Then("user validate the Television namee")
	public void user_validate_the_Television_namee() throws Exception {
		WebElement text1 = driver.findElement(By.xpath("(//span[contains(text(),'TV')])[2]"));
		String text = text1.getText();
		System.out.println("second page:"+text);
		//excel read 
		File f=new File(".//Excel//Television.xlsx");
		FileInputStream f1=new FileInputStream(f);
		Workbook w=new XSSFWorkbook(f1);
		Sheet sheet = w.getSheet("bargan1");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String stringCellValue = cell.getStringCellValue();
	    System.out.println("read excel data:"+stringCellValue);
	    if(text.equals(stringCellValue)) {
	    	System.out.println("pass");
	    }else {
	    	System.out.println("fail");
	    }
	    
	}

}
