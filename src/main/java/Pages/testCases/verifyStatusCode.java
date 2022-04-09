package Pages.testCases;

import BasePackage.BasePage;
import com.jayway.restassured.RestAssured;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class verifyStatusCode extends BasePage {

    public WebDriver webDriver;

    public verifyStatusCode(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void findHyperlinks(String webUrl) throws IOException {
        Document doc = Jsoup.connect(webUrl).get();
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            if (link.attr("href").startsWith("https://www.mytheresa.com/en-de/men")) {
                validateStatus(link.attr("href"));
            }
        }
    }

    public void validateStatus(String url) {
        try {
            int code = RestAssured.get(url).statusCode();
            Assert.assertEquals(200, code);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}