
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestePaginaSoma {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);  
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\phantomjs.exe");
        
        driver = new PhantomJSDriver(caps);
        baseUrl = "http://localhost:30158/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testE() throws Exception {
        driver.get(baseUrl + "OlaWeb/");
        driver.findElement(By.name("p1")).clear();
        driver.findElement(By.name("p1")).sendKeys("5");
        driver.findElement(By.name("p2")).clear();
        driver.findElement(By.name("p2")).sendKeys("6");
        driver.findElement(By.name("calcular")).click();
        assertEquals("O resultado foi: 11", driver.findElement(By.cssSelector("h1")).getText());
    }

    @Test
    public void testE2() throws Exception {
        driver.get(baseUrl + "OlaWeb/");
        driver.findElement(By.name("p1")).clear();
        driver.findElement(By.name("p1")).sendKeys("13");
        driver.findElement(By.name("p2")).clear();
        driver.findElement(By.name("p2")).sendKeys("9");
        driver.findElement(By.name("calcular")).click();
        assertEquals("O resultado foi: 22", driver.findElement(By.cssSelector("h1")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
