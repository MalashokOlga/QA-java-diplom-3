package constructor;

import extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.StellarBurgers;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestSwitchIngredient {
    private WebDriver driver;
    @Parameterized.Parameter
    public String ingredient;

    @Parameterized.Parameters(name = "{index}: данные для переключения между меню игредиентов")
    public static Object[] ingredientData() {
        return new Object[] {"Булки", "Соусы", "Начинки"};
    }
    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @Test
    public void testSwitchIngredient() throws InterruptedException {
        StellarBurgers stellarBurgers = new StellarBurgers(driver);
        assertEquals(ingredient, stellarBurgers.switchIngredient(ingredient));
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
