package page;

import api.UserCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;
    private final By linkRegister = By.xpath(".//*[@class='Auth_link__1fOlj' and @href='/register']");
    private final By userEmail = By.xpath(".//*[@class='text input__textfield text_type_main-default' and @name='name']");
    private final By userPassword = By.xpath(".//*[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private final By btnLogin = By.xpath(".//*[text()='Войти']");
    private final By btnMakeOrder = By.xpath(".//*[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']");
    private final By linkResetPassword = By.xpath(".//*[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");
    private final By linkLoginResetPassword = By.xpath(".//*[@class='Auth_link__1fOlj' and text()='Войти']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public LoginPage setUserEmail(String email) {
        driver.findElement(userEmail).sendKeys(email);
        return this;
    }
    public LoginPage setUserPassword(String password) {
        driver.findElement(userPassword).sendKeys(password);
        return this;
    }
    public LoginPage clickBtnLogin() {
        driver.findElement(btnLogin).click();
        return this;
    }
    public LoginPage clickLinkResetPassword() {
        driver.findElement(linkResetPassword).click();
        return this;
    }
    public LoginPage clickLinkLoginResetPassword() {
        driver.findElement(linkLoginResetPassword).click();
        return this;
    }
    public LoginPage clickLinkRegister() throws InterruptedException {
        WebElement element = driver.findElement(linkRegister);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(500);
        driver.findElement(linkRegister).click();
        return this;
    }
    public boolean successLogin(UserCredentials userCredentials) throws InterruptedException {
        setUserEmail(userCredentials.getEmail());
        setUserPassword(userCredentials.getPassword());
        clickBtnLogin();
        Thread.sleep(500);
        return driver.findElement(btnMakeOrder).isEnabled();
    }
    public boolean loginPageAppears() throws InterruptedException {
        Thread.sleep(500);
        return driver.findElement(btnLogin).isEnabled();
    }
}
