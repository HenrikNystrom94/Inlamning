package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    private WebDriver driver1;
    private WebDriver driver2;

    @Before
    public void setUp() {
        System.out.println("Setting up browser");
    }

    @After
    public void tearDown() {
        System.out.println("Closing");
    }


    @Given("I am am using {string} and {string} as browsers")
    public void iAmAmUsingAsBrowse(String browser1, String browser2) {

       driver1 = getBrowser(browser1);
       driver2 = getBrowser(browser2);
    }

    @Given("I have navigated to the basketball England registration page")
    public void iHaveARegistrationPage() {

        driver1.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver2.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }


    @Given("I enter {string} in the date of birth field")
    public void iEnterInThe(String birthDate) {
        driver1.findElement(By.cssSelector("input.custom-date")).sendKeys(birthDate);
        driver2.findElement(By.cssSelector("input.custom-date")).sendKeys(birthDate);
    }

    @And("I enter {string} in the first name field")
    public void iEnterInTheFirstName(String name) {

        driver1.findElement(By.cssSelector("input#member_firstname")).sendKeys(name);
        driver2.findElement(By.cssSelector("input#member_firstname")).sendKeys(name);
    }

    @And("I enter {string} in the last name field")
    public void iEnterInTheLastName(String lastName) {

        driver1.findElement(By.cssSelector("input#member_lastname")).sendKeys(lastName);
        driver2.findElement(By.cssSelector("input#member_lastname")).sendKeys(lastName);
    }

    @And("I enter {string} in the email fields")
    public void iEnterInTheEmail(String email) {

        String email1 = emailGenerator(email);
        String email2 = emailGenerator(email);

        driver1.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(email1);
        driver1.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(email1);

        driver2.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(email2);
        driver2.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(email2);
    }

    @And("I enter {string} in the password field")
    public void iEnterInThePassword(String password) {
        driver1.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys(password);
        driver2.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys(password);
    }

    @And("I enter {string} in the retype password field")
    public void iEnterInTheRetypePassword(String password) {
        driver1.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys(password);
        driver2.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys(password);
    }

    @And("I click on {string}")
    public void iClickOn(String termsAndConditionBox) {
        if (!termsAndConditionBox.isEmpty()) { //Går inte vidare om driver körs med ingenting i termsAndConditionBox
            click(driver1, By.cssSelector(termsAndConditionBox));
            click(driver2, By.cssSelector(termsAndConditionBox));
        }
    }

    @And("I click on age verification box")
    public void iClickOnAgeVerification() {

        click(driver1, By.cssSelector("label[for='sign_up_26']"));
        click(driver2, By.cssSelector("label[for='sign_up_26']"));
    }

    @And("I click on ethics and conduct box")
    public void iClickOnEthicsAndConduct() {

        click(driver1, By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"));
        click(driver2, By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"));
    }

    @When("I click on confirm and join button")
    public void iClickOnConfirmAndJoin() {

        click(driver1, By.cssSelector("input[type='submit']"));
        click(driver2, By.cssSelector("input[type='submit']"));

    }

    @Then("{string} is visible on {string}")
    public void isVisibleInTheRegistrationCompletePage(String expected, String verification) {

        String actual1 = driver1.findElement(By.cssSelector(verification)).getText();
        String actual2 = driver2.findElement(By.cssSelector(verification)).getText();

        assertEquals(expected, actual1);
        assertEquals(expected, actual2);

        driver1.quit();
        driver2.quit();

    }

    private static void click(WebDriver driver, By by) {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }
    private static String emailGenerator(String email){

        StringBuilder sb = new StringBuilder(6);
        Random random = new Random();
        for(int i = 0; i<5; i++){
            sb.append(random.nextInt(10));
        }
        String randomNum = sb.toString();

        String[] parts = email.split("@");

        email = parts[0] + randomNum + "@" + parts[1];

        return email;

    }
    private static WebDriver getBrowser(String browser) {
        WebDriver driver;
        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();

        } else if (browser.equals("edge")) {
            driver = new EdgeDriver();

        } else {
            driver = new ChromeDriver();

        }
        return driver;
    }


}
