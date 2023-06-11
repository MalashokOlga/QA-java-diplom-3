package register;

import api.User;
import api.UserGenerator;
import extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.RegisterPage;
import page.StellarBurgers;

import static org.junit.Assert.assertTrue;

public class TestUserRegisterShortPassword {
    private static User user;
    private WebDriver driver;
    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        user = UserGenerator.getFaker();
        user.setPassword("12345");
    }

    @Test
    public void testUserRegisterShortPassword() throws InterruptedException {
        StellarBurgers stellarBurgers = new StellarBurgers(driver);
        stellarBurgers.clickBtnLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLinkRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue(registerPage.unsuccessUserRegister(user));
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
