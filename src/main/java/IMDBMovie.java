import java.util.ArrayList;
import java.util.Arrays;

public class IMDBMovie {
    public String title;
    public int time;
    public float rate;
    public ArrayList<String> directors;
    public int metascore;
    public int year;
    public ArrayList<String> castList;
    public ArrayList<String> genresList;

    public void info() {
        System.out.println("Title: " + title);
        System.out.println("Time (in mins): " + time);
        System.out.println("IMDB Rating: " + rate);
        System.out.println("Metascore Rating: " + metascore);
        System.out.println("Year: " + year);
        if (castList != null) {
            System.out.println("Cast: " + String.join(", ", castList));
        } else {
            System.out.println("Cast list is undefined");
        }
        if (genresList != null) {
            System.out.println("Genres: " + String.join(", ", genresList));
        } else {
            System.out.println("Genres list is undefined");
        }
        if (directors != null) {
            System.out.println("Directors   : " + String.join(", ", directors));
        } else {
            System.out.println("Director list is undefined");
        }

    }
}
