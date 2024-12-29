package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    private By bodySection = By.tagName("body");
    private By opinionSection = By.linkText("Opinión");
    private By accept = By.cssSelector("button[id*='agree']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getBody() {
        return driver.findElement(bodySection).getText();
    }

    public void goToOpinionSection() {
        driver.findElement(accept).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(opinionSection).click();
    }

}
