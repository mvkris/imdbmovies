import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieParser {

    public static IMDBMovie parsePage(WebDriver driver) {
        IMDBMovie movie = new IMDBMovie();
        WebElement time = driver.findElement(By.xpath("//div[@class=\"txt-block\"]/time"));
        String textTime = time.getText().split(" ")[0];
        movie.time = Integer.parseInt(textTime);


        WebElement title = driver.findElement(By.xpath("//div[@class=\"title_wrapper\"]/h1"));
        String[] titleList = title.getText().split(" ");

        movie.title = String.join(" ", Arrays.copyOfRange(titleList, 0, titleList.length - 1));

        WebElement rate = driver.findElement(By.xpath("//div[@class=\"ratingValue\"]/strong/span"));
        movie.rate = Float.valueOf(rate.getText());


        try {
            WebElement director = driver.findElement(By.xpath("//h4[text()=\"Director:\"]/following-sibling::a"));
            movie.directors = new ArrayList<>();
            movie.directors.add(director.getText());
        } catch (NoSuchElementException e) {
            List<WebElement> directorsList = driver.findElements(By.xpath("//h4[text()=\"Directors:\"]/following-sibling::a"));

            movie.directors = new ArrayList<>();
            for (int k2 = 0; k2 < directorsList.size(); k2 ++ ) {
                movie.directors.add(directorsList.get(k2).getText());
            }
        }

        try{
            WebElement metascore = driver.findElement(By.xpath("//div[contains(@class, \"metacriticScore\")]/span"));
            movie.metascore = Integer.parseInt(metascore.getText());
        } catch (NoSuchElementException e) {
            movie.metascore = 0;
        }



        WebElement year = driver.findElement(By.xpath("//div[@class=\"title_wrapper\"]/h1/span/a"));
        movie.year = Integer.parseInt(year.getText());

        List<WebElement> castList = driver.findElements(By.xpath("//table[@class=\"cast_list\"]//tr/td[2]/a"));

        ArrayList<String> stringCastList = new ArrayList<String>();

        for (int m = 0; m < castList.size(); m++) {
            stringCastList.add(castList.get(m).getText());

        }


        movie.castList = stringCastList;


        List<WebElement> genresList = driver.findElements(By.xpath("//h4[text()=\"Genres:\"]/following-sibling::a"));

        ArrayList<String> stringGenresList = new ArrayList<String>();

        for (int s = 0; s < genresList.size(); s++) {
            stringGenresList.add(genresList.get(s).getText());
        }


        return movie;
    }
}
