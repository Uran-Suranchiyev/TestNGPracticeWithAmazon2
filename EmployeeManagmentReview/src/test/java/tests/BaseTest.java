package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    @BeforeMethod
    public void BaseSetUp() {
        initializeDriver();
    }

    @AfterMethod
    public void BaseTearDown() {
        driver.quit();
    }

    public void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.readProperty("config.properties", "homeURL"));
    }

}

