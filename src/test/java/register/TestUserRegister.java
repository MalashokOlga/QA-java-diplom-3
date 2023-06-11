package register;

import api.User;
import api.UserClient;
import api.UserCredentials;
import api.UserGenerator;
import extensions.WebDriverFactory;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.RegisterPage;
import page.StellarBurgers;

import static org.junit.Assert.assertTrue;

public class TestUserRegister {
    private UserClient userClient;
    private static User user;
    private String accessToken;
    private WebDriver driver;
    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        userClient = new UserClient();
        user = UserGenerator.getFaker();
    }

    @Test
    public void testUserRegister() throws InterruptedException {
        StellarBurgers stellarBurgers = new StellarBurgers(driver);
        stellarBurgers.clickBtnLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLinkRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue(registerPage.successUserRegister(user));
    }
    @After
    public void teardown() {
        ValidatableResponse loginResponse = userClient.login(UserCredentials.from(user));
        accessToken = loginResponse.extract().path("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
        userClient.delete(accessToken);
        driver.quit();
    }
}
