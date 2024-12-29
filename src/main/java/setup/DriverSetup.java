package setup;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.URL;

public class DriverSetup {
    protected WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "os", "version","os_version"})
    public void driverSettings(String browser, String platform,String version, String osVersion) throws Exception {
        String testMachine = Config.getProperty("test_machine");

        if ("local".equalsIgnoreCase(testMachine)) {
            // Setup for local execution
            if ("chrome".equalsIgnoreCase(browser)) {
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
            }
            else if ("firefox".equalsIgnoreCase(browser)) {
                System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
                driver = new FirefoxDriver();
            } else if ("edge".equalsIgnoreCase(browser)) {
                System.setProperty("webdriver.edge.driver", "/path/to/msedgedriver");
                driver = new EdgeDriver();
            }
            else {
                throw new IllegalArgumentException("Unsupported browser for local execution: " + browser);
            }
        } else if ("cloud".equalsIgnoreCase(testMachine)) {
            // Setup for BrowserStack execution
            String username = Config.getProperty("browserstack.username");
            String accessKey = Config.getProperty("browserstack.access_key");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browser", browser);
            capabilities.setCapability("browser_version", version);
            capabilities.setCapability("os", platform);
            capabilities.setCapability("os_version", osVersion);
            capabilities.setCapability("name", "Assessment Test on BrowserStack");


            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), capabilities);
        } else {
            throw new IllegalArgumentException("Invalid testMachine: " + testMachine);
        }

        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
