import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class IMDBParser {

    public static IMDBMovie parsePage(WebElement webElement) {
        IMDBMovie movie = new IMDBMovie();
        WebElement time = webElement.findElement(By.xpath(".//span[@class=\"runtime\"]"));
        String textTime = time.getText().split(" ")[0];
        movie.time = Integer.parseInt(textTime);




        WebElement title = webElement.findElement(By.xpath(".//h3/a"));
        movie.title = title.getText();



        WebElement rate = webElement.findElement(By.xpath(".//div[@class=\"inline-block ratings-imdb-rating\"]/strong"));
        movie.rate = Float.valueOf(rate.getText().replace(",", "."));


        List<WebElement> directors = webElement.findElements(By.xpath(".//span[@class=\"ghost\"]//preceding-sibling::a"));
        movie.directors = new ArrayList<>();

        for (int i = 0; i < directors.size(); i++) {
            movie.directors.add(directors.get(i).getText());
        }


        List<WebElement> castList = webElement.findElements(By.xpath(".//span[@class=\"ghost\"]//following-sibling::a"));
        movie.castList = new ArrayList<>();
        for (int k = 0; k < castList.size(); k++) {
            movie.castList.add(castList.get(k).getText());
        }

        try{
            WebElement metascore = webElement.findElement(By.xpath(".//span[contains(@class, \"metascore\")]"));
            movie.metascore = Integer.parseInt(metascore.getText());
        } catch (NoSuchElementException e) {
            movie.metascore = 0;
        }




        WebElement year = webElement.findElement(By.xpath(".//span[@class=\"lister-item-year text-muted unbold\"]"));
        String textYear = year.getText();
        textYear = textYear.substring(textYear.lastIndexOf("(") + 1, textYear.lastIndexOf(")"));
        movie.year = Integer.parseInt(textYear);

        WebElement genresList = webElement.findElement(By.xpath(".//span[@class=\"genre\"]"));
        String[] genres = genresList.getText().split(", ");

        movie.genresList = new ArrayList<String>();

        for (int l = 0; l < genres.length; l++) {
            movie.genresList.add(genres[l]);
        }


        return movie;
    }
}
