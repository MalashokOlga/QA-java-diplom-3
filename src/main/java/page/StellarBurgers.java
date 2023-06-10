package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class StellarBurgers {
    private final WebDriver driver;
    String currentIngredient;
    private final By btnLogin = By.xpath(".//*[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Войти в аккаунт']");
    private final By btnLK = By.xpath(".//*[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    private final By btnMakeOrder = By.xpath(".//*[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']");
    private final By btnBun = By.xpath(".//*[@class='text text_type_main-default' and text()='Булки']");
    private final By btnSauce = By.xpath(".//*[@class='text text_type_main-default' and text()='Соусы']");
    private final By btnFilling = By.xpath(".//*[@class='text text_type_main-default' and text()='Начинки']");
    private final By headerBun = By.xpath(".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Булки']");
    private final By headerSauce = By.xpath(".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Соусы']");
    private final By headerFilling = By.xpath(".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Начинки']");
    public StellarBurgers(WebDriver driver) {
        this.driver = driver;
    }
    public StellarBurgers clickBtnLogin() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(btnLogin).click();
        return this;
    }
    public StellarBurgers clickBtnLk() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(btnLK).click();
        return this;
    }
    public boolean successMainPage() throws InterruptedException {
        Thread.sleep(500);
        return driver.findElement(btnMakeOrder).isEnabled();
    }
    public String switchIngredient(String ingredient) throws InterruptedException {
        Thread.sleep(2000);
        switch (ingredient) {
            case "Булки":
                if (!(driver.findElement(btnBun).isEnabled())) {driver.findElement(btnBun).click();}
                if (driver.findElement(headerBun).isDisplayed()) {
                    currentIngredient = driver.findElement(headerBun).getText();
                }
                break;
            case "Соусы":
                driver.findElement(btnSauce).click();
                if (driver.findElement(headerSauce).isDisplayed()) {
                    currentIngredient = driver.findElement(headerSauce).getText();
                }
                break;
            case "Начинки":
                driver.findElement(btnFilling).click();
                if (driver.findElement(headerFilling).isDisplayed()) {
                    currentIngredient = driver.findElement(headerFilling).getText();
                }
                break;
        }
        return currentIngredient;
    }
}
