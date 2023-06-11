package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileLK {
    private final WebDriver driver;
    private final By logoStellarBurger = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");
    private final By btnConstructor = By.xpath(".//*[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");
    private final By btnExit = By.xpath(".//*[text()='Выход']");
    public ProfileLK(WebDriver driver) {
        this.driver = driver;
    }
    public ProfileLK clickLogo() {
        driver.findElement(logoStellarBurger).click();
        return this;
    }
    public ProfileLK clickConstructor() {
        driver.findElement(btnConstructor).click();
        return this;
    }
    public ProfileLK clickExit() {
        driver.findElement(btnExit).click();
        return this;
    }
    public boolean successEnterLk() throws InterruptedException {
        Thread.sleep(500);
        return driver.findElement(btnExit).isEnabled();
    }
}
