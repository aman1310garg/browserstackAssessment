package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OpinionPage {
    private WebDriver driver;

    private By articles = By.cssSelector("article");
    private By articleTitle = By.tagName("h2");
    private By articleContent = By.tagName("p");
    private By articleImage = By.tagName("img");

    public OpinionPage(WebDriver driver) {
        this.driver = driver;
    }


    public List<WebElement> getArticles() {
        return driver.findElements(articles);
    }

    public String getArticleTitle(WebElement article) {
        return article.findElement(articleTitle).getText();
    }

    public String getArticleContent(WebElement article) {
        return article.findElement(articleContent).getText();
    }

    public String getArticleImage(WebElement article) {
        return article.findElement(articleImage).getAttribute("src");
    }

    public WebElement getImage(WebElement article) {
        try {
            return article.findElement(articleImage);
        }
        catch (Exception e) {
            return null;
        }
    }
}
