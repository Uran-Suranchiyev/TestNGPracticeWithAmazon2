package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.BrowserUtils;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage{
    private WebDriver driver;
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[text()='All']")
    public  WebElement allBtn;

    @FindBy(xpath = "//a[@class='navbar-brand']/h5")
    public WebElement pageWelcomeTxt;

    @FindBy(name = "id")
    public WebElement idField;

    @FindBy(name = "firstName")
    public WebElement firstNameField;

    @FindBy(name = "lastName")
    public WebElement lastNameField;

    @FindBy(name = "role")
    public WebElement roleField;

    @FindBy(name = "department")
    public WebElement departmentField;

    @FindBy(xpath = "//button[text()='Enter Employee']")
    public WebElement enterEmployeeBtn;

    @FindBy(xpath = "//tbody//th")
    public List<WebElement> allEmployeeIds;

    @FindBy(xpath = "//div//div[@class='RoleClass']//tr")
    public List<WebElement> allRoles;

    @FindBy(xpath = "//div//div[@class='dep']//tr")
    public List<WebElement> allDepartmens;

    @FindBy(xpath = "//div//div[@class='RoleClass']")
    public WebElement roleTable;

    @FindBy(xpath = "//div[@class='dep']//div[@class='RoleClass']//table")
    public WebElement departmentTable;

    @FindBy(xpath = "//button[text()='Confirm']")
    public WebElement confirmBtn;

    @FindBy(name = "keyword")
    public WebElement filterField;

    @FindBy(xpath = "//button[text()='Search']")
    public WebElement searchBtn;

    @FindBy(id = "inputArea1")
    public WebElement addroleField;

    @FindBy(id = "inputArea2")
    public WebElement addDepField;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/button[1]")
    public WebElement addRoleBtn;

    @FindBy(xpath = "//div[@class='dep']//button[@class='btn btn-success'][normalize-space()='Add']")
    public WebElement addDepBtn;
    public String addNewEmployee() {
        Faker faker = new Faker();

        String id = faker.idNumber() + "";

        idField.sendKeys(id);
        firstNameField.sendKeys(faker.name().firstName());
        lastNameField.sendKeys(faker.name().lastName());

        BrowserUtils.selectByIndex(roleField, 1);
        BrowserUtils.selectByIndex(departmentField, 1);

        enterEmployeeBtn.click();

        return id;
    }

    public void deleteRandomEmployee() {
        Random random = new Random();
        int randomIndex = random.nextInt(allEmployeeIds.size());
        String id = allEmployeeIds.get(randomIndex).getText() + "";
        WebElement deleteBtn = driver.findElement(By.xpath("//th[text()='"+ id + "']/following::button[2]"));

        deleteBtn.click();

        confirmBtn.click();

    }

    public void deleteRoleOrDepBytext(String input) {
        driver.findElement(By.xpath("//td[contains(text(), '"+ input + "')]/button")).click();
    }

    public void verifyRoleOrDepAddedToList(List<WebElement> listOfValues, String value) {
        for (WebElement each : allRoles) {
            if (each.getText().contains(value)) {
                Assert.assertTrue(true);
                // delete role by text
                deleteRoleOrDepBytext(value);
                return;
            }
        }
        Assert.fail();
    }

}
