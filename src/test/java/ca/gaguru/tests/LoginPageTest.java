package ca.gaguru.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    WebDriver driver;
    public static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/";

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void validLogin() {
        driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='txtPassword']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("input#btnLogin")).click();

    }

    @Test
    public void invalidLogin() {
        driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='txtPassword']")).sendKeys("admin123456");
        driver.findElement(By.cssSelector("input#btnLogin")).click();
        String msg = driver.findElement(By.xpath("//*[@id='spanMessage']")).getText();
        Assert.assertEquals(msg, "Invalid credentials");

    }
}

