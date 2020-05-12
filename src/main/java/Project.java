import com.google.common.collect.BiMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class Project {
    private static String imdbURL = "https://www.imdb.com/search/title/?groups=top_250&sort=user_rating";

    private static WebDriver driver;
    private static ArrayList<IMDBMovie> filmList;
    private static WebDriverWait wait;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(imdbURL);
        wait = new WebDriverWait(driver, 10);
        filmList = new ArrayList<IMDBMovie>();

        parsePageElements();

        while (!driver.findElements(By.xpath("//a[@class=\"lister-page-next next-page\"]")).isEmpty()) {
            driver.findElement(By.xpath("//a[@class=\"lister-page-next next-page\"]")).click();
            parsePageElements();
        }


        ParserOutput.showMoviesByYear(filmList);
        ParserOutput.showMoviesByMetascore(filmList);
        ParserOutput.showTheShortestMovie(filmList);
        ParserOutput.showNumberOfMoviesByDirector(filmList);
        ParserOutput.showDirectorsByAverageRate(filmList);
        ParserOutput.showActorsByAverageRate(filmList);

        driver.close();
    }

    static void parsePageElements() {

        List<WebElement> moviesElements = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class=\"lister-item-content\"]"), 50));
        for (int i = 0; i < moviesElements.size(); i++) {
            IMDBMovie movie = IMDBParser.parsePage(moviesElements.get(i));
            filmList.add(movie);
            if (filmList.size() % 10 == 0) {
                System.out.println(filmList.size() + " movies parsed");
            }
        }
    }
}
