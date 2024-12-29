package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.OpinionPage;
import setup.DriverSetup;
import util.DownloadUtil;
import util.TextMappingUtil;
import util.TranslationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssessmentTest extends DriverSetup {
    @Test
    public void allTests() throws Exception {
        driver.get("https://elpais.com/");

        HomePage homePage = new HomePage(driver);
        if(homePage.getBody().contains("\"España\""))
        {
            System.out.println("Website content is in spanish");
        }

          homePage.goToOpinionSection();
        
        OpinionPage opinionPage = new OpinionPage(driver);

        List<WebElement> articles = opinionPage.getArticles();
        List<String> translatedHeaders = new ArrayList<>();

        for (int i = 0; i < Math.min(articles.size(), 5); i++) {
            String title = opinionPage.getArticleTitle(articles.get(i));
            System.out.println("Spanish title for article " + i+1 + title);
            String content = opinionPage.getArticleContent(articles.get(i));
            System.out.println("Spanish content for article "+ i+1 + content);
            WebElement img = opinionPage.getImage(articles.get(i));
            if (img != null) {
                String imageUrl = img.getAttribute("src");
                DownloadUtil.downloadImage(imageUrl);
            }

            String translatedTitle = TranslationUtil.translateProvidedText(title);
            translatedHeaders.add(translatedTitle);
        }

        Map<String, Integer> wordAnalysis = TextMappingUtil.checkRepeatedWords(translatedHeaders);
        System.out.println("Repeated words: " + wordAnalysis);
    }
}

