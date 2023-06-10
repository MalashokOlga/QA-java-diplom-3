package profile;

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
import page.ProfileLK;
import page.StellarBurgers;

import static org.junit.Assert.assertEquals;

public class TestProfileExit {
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
    public void testProfileExit() throws InterruptedException {
        StellarBurgers stellarBurgers = new StellarBurgers(driver);
        stellarBurgers.clickBtnLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.successLogin(UserCredentials.from(user));
        stellarBurgers.clickBtnLk();
        ProfileLK profileLK = new ProfileLK(driver);
        profileLK.clickExit();
        assertEquals(true, loginPage.loginPageAppears());
    }
    @After
    public void teardown() {
        userClient.delete(accessToken);
        driver.quit();
    }
}
