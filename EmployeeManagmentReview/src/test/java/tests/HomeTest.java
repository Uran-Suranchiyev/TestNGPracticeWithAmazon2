package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BrowserUtils;

public class HomeTest extends BaseTest{
    private HomePage page;
    private LoginPage loginPage;
    @BeforeMethod
    public void setUp() {
        page = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }
    @Test(testName = "As a user I should be able to add a new employee")
    public void test01(){
        // login as a user
        loginPage.signInAsDefaultUser();

        //fill out the user form on homePage
        String expectedID = page.addNewEmployee();

        //verify new employee was added to the table

        BrowserUtils.sleep(2000L);

        for (WebElement each : page.allEmployeeIds) {
            if (each.getText().equalsIgnoreCase(expectedID)) {
                Assert.assertTrue(true);
                return;
            }
        }

        Assert.fail();

    }
    @Test(testName = "As a admin I should be able to add a new employee")
    public void test02(){
        // login as a admin
        loginPage.signInAsAdmin();

        //fill out the user form on homePage
        String expectedID = page.addNewEmployee();

        //verify new employee was added to the table

        BrowserUtils.sleep(2000L);

        for (WebElement each : page.allEmployeeIds) {
            if (each.getText().equalsIgnoreCase(expectedID)) {
                Assert.assertTrue(true);
                return;
            }
        }
        Assert.fail();
    }

    @Test(testName = "As an admin I should see following tables: Enter Role, Enter Department")
    public void test03(){
        // login as a admin
        loginPage.signInAsAdmin();

        //Verify Enter Role is displayed
        Assert.assertTrue(page.roleTable.isDisplayed());
        //Verify Enter Department is displayed
        Assert.assertTrue(page.departmentTable.isDisplayed());
    }

    @Test(testName = "As an admin I should be able to delete an existing employee")
    public void test04(){
        // login as a admin
        loginPage.signInAsAdmin();

        page.allBtn.click();

        BrowserUtils.sleep(3000L);

        int beforeDelete = page.allEmployeeIds.size();

        //delete an existing employee
        page.deleteRandomEmployee();

        BrowserUtils.sleep(3000L);

        int afterDelete = page.allEmployeeIds.size();

        //Verify is employee already deleted
        Assert.assertEquals(afterDelete, beforeDelete - 1);

    }

    @Test(testName = "As a user I should be able to filter table view based on the search keyword")
    public void test05() {
        // login as a user
        loginPage.signInAsDefaultUser();

        //fill out the user form on homePage
        String expectedID = page.addNewEmployee();

        BrowserUtils.sleep(2000L);

        // filter table view
        page.filterField.sendKeys(expectedID);
        page.searchBtn.click();

        //verify table was filtered
        for (WebElement each : page.allEmployeeIds) {
            if (each.getText().equalsIgnoreCase(expectedID)) {
                Assert.assertTrue(true);
                return;
            }
        }
        Assert.fail();
    }

    @Test(testName = "As an admin I should have an option to create new Role")
    public void test06() {
        //login as admin
        loginPage.signInAsAdmin();

        Faker faker = new Faker();
        String newRole = faker.name().title();
        // create new Role
        page.addroleField.sendKeys(newRole);
        page.addRoleBtn.click();

        BrowserUtils.sleep(3000l);

        // verify role is created
        page.verifyRoleOrDepAddedToList(page.allRoles, newRole);

    }

    @Test(testName = "As an admin I should have an option to add a new Department\n" +
            "\n")
    public void test07() {
        //login as admin
        loginPage.signInAsAdmin();

        Faker faker = new Faker();
        String newDep = faker.name().title();
        // create new Role
        page.addDepField.sendKeys(newDep);
        page.addDepBtn.click();

        BrowserUtils.sleep(3000l);

        // verify role is created
        page.verifyRoleOrDepAddedToList(page.allDepartmens, newDep);


    }

}
