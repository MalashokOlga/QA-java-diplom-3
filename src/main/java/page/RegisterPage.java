package page;

import api.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class RegisterPage {
    private final WebDriver driver;
    private final By userName = By.xpath(".//*[@class='text input__textfield text_type_main-default' and @name='name']");
    private final By userEmail = By.xpath("(.//*[@class='text input__textfield text_type_main-default'])[2]");
    private final By userPassword = By.xpath(".//*[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private final By btnRegister = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By headerEnter = By.xpath(".//*[text()='Вход']");
    private final By badPassword = By.xpath(".//*[@class='input__error text_type_main-default' and text()='Некорректный пароль']");
    private final By linkLoginReg = By.xpath(".//*[@class='Auth_link__1fOlj' and text()='Войти']");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public RegisterPage setUserName(String name) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(userName).sendKeys(name);
        return this;
    }
    public RegisterPage setUserEmail(String email) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(userEmail).sendKeys(email);
        return this;
    }
    public RegisterPage setUserPassword(String password) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(userPassword).sendKeys(password);
        return this;
    }
    public RegisterPage clickBtnRegister() {
        driver.findElement(btnRegister).click();
        return this;
    }
    public RegisterPage clickLinkLoginReg() {
        driver.findElement(linkLoginReg).click();
        return this;
    }
    public boolean successUserRegister(User user) throws InterruptedException {
        setUserName(user.getName());
        setUserEmail(user.getEmail());
        setUserPassword(user.getPassword());
        WebElement element = driver.findElement(btnRegister);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(500);
        clickBtnRegister();
        return driver.findElement(headerEnter).isEnabled();
    }
    public boolean unsuccessUserRegister(User user) throws InterruptedException {
        setUserName(user.getName());
        setUserEmail(user.getEmail());
        setUserPassword(user.getPassword());
        WebElement element = driver.findElement(btnRegister);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(500);
        clickBtnRegister();
        return driver.findElement(badPassword).isEnabled();
    }
}
