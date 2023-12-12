package testes;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import testes2.WebDriverWait;

public class createcontacts {
    
    WebDriver driver;
   
    @BeforeSuite
    public void login(){

        System.setProperty("webdriver.chrome,driver","\\Users\\bdasilva\\Documents\\java\\dem\\src\\drivers\\chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dhp000003szikma4-dev-ed.develop.lightning.force.com/lightning/page/home");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement username = driver.findElement(By.id("username"));
        username.click();
        username.sendKeys("bdasilva@salesforce1.com");

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys("Salesforce1");

        WebElement login = driver.findElement(By.id("Login"));
        login.click();
    }
    
    //@BeforeMethod
    //public void loginsalesforce(){
    //  }


    @Test
    public void create(){
        WebDriver driver = this.driver;

        WebElement button = driver.findElement(By.xpath("//span[text()='Contacts']/parent::a[contains(@href,'lightning/o/Contact/home')]"));   
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", button);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='New' and @type='button']")));        
        driver.findElement(By.xpath("//button[text()='New' and @type='button']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']"))); 
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Marcela");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastName']"))); 
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Caixeta");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Phone']"))); 
        driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys("67367289");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Email']"))); 
        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("teste@teste.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save' and @type='button']"))); 
        driver.findElement(By.xpath("//button[text()='Save' and @type='button']")).click();

    }

    @Test
    public void verifyFieldisEmpyt(){

        WebElement button = driver.findElement(By.xpath("//span[text()='Contacts']/parent::a[contains(@href,'lightning/o/Contact/home')]"));    
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", button);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title=\"Rose Gonzalez\"]")));
        WebElement search = driver.findElement(By.xpath("//a[@title=\"Rose Gonzalez\"]"));
        search.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title=\"Details\"]")));
        WebElement details = driver.findElement(By.xpath("//li[@title=\"Details\"]"));
        details.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='test-id__inline-edit-trigger slds-shrink-none inline-edit-trigger slds-button slds-button_icon-bare' and @title='Edit Title']")));
        WebElement buttonTitle = driver.findElement(By.xpath("//button[@class='test-id__inline-edit-trigger slds-shrink-none inline-edit-trigger slds-button slds-button_icon-bare' and @title='Edit Title']"));
        buttonTitle.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Title']")));
        WebElement Title = driver.findElement(By.xpath("//input[@name='Title']"));

        String textInsidetitleBox = Title.getAttribute("value");

        // Check whether input field is blank

        if(textInsidetitleBox.isEmpty())
        {
            System.out.println("Input field is empty");
        }
        else
        {
            System.out.println(textInsidetitleBox);
        }
    }
}

