package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;

public class LoginPage extends BasePage{
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(name = "username")
    public WebElement usernameField;

    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign in']")
    public WebElement signInBtn;

    public void signIn(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        signInBtn.click();
    }

    public void signInAsDefaultUser() {
        usernameField.sendKeys(ConfigReader.readProperty("config.properties", "defaultUsername"));
        passwordField.sendKeys(ConfigReader.readProperty("config.properties", "defaultPassword"));
        signInBtn.click();
    }

    public void signInAsAdmin() {
        usernameField.sendKeys(ConfigReader.readProperty("config.properties", "adminUsername"));
        passwordField.sendKeys(ConfigReader.readProperty("config.properties", "adminPassword"));
        signInBtn.click();
    }

}
