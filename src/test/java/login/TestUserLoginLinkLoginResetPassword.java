package login;

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
import page.StellarBurgers;

import static org.junit.Assert.assertEquals;

public class TestUserLoginLinkLoginResetPassword {
    private UserClient userClient;
    private static User user;
    private String accessToken;
    private WebDriver driver;
    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        userClient = new UserClient();
        user = UserGenerator.getFaker();
        ValidatableResponse createResponse = userClient.create(user);
        accessToken = createResponse.extract().path("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
    }

    @Test
    public void testUserLoginLinkLoginResetPassword() throws InterruptedException {
        StellarBurgers stellarBurgers = new StellarBurgers(driver);
        stellarBurgers.clickBtnLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLinkResetPassword();
        loginPage.clickLinkLoginResetPassword();
        assertEquals(true, loginPage.successLogin(UserCredentials.from(user)));
    }
    @After
    public void teardown() {
        userClient.delete(accessToken);
        driver.quit();
    }
}
