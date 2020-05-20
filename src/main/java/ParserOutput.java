import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserOutput {

    public static void showMoviesByYear(List<IMDBMovie> filmList) {
        filmList.sort((movie1, movie2) -> movie1.year - movie2.year);
        System.out.println("");
        System.out.println("TOP 250 by year");
        for (int i = 0; i < filmList.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + filmList.get(i).title + " - " + filmList.get(i).year);
        }
    }

    public static void showMoviesByMetascore(List<IMDBMovie> filmList) {
        filmList.sort((movie1, movie2) -> movie2.metascore - movie1.metascore);
        System.out.println("");
        System.out.println("TOP 250 by metascore");
        for (int i = 0; i < filmList.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + filmList.get(i).title + " - " + filmList.get(i).metascore);
        }
    }

    public static void showTheShortestMovie(List<IMDBMovie> filmList) {
        filmList.sort((movie1, movie2) -> movie1.time - movie2.time);
        System.out.println("");
        System.out.println("The shortest movie of TOP 250");
        System.out.println(filmList.get(0).title + " - " + filmList.get(0).time);
    }

    public static void showNumberOfMoviesByDirector(List<IMDBMovie> filmList) {
        System.out.println("");
        System.out.println("The number of movies by director");
        Map<String, Integer> directorMap = new HashMap<String, Integer>();
        for (int i = 0; i < filmList.size(); i++) {
            ArrayList<String> directors = filmList.get(i).directors;
            for (int k = 0; k < directors.size(); k++) {
                String director = directors.get(k);
                if (directorMap.containsKey(director)) {
                    directorMap.put(director, directorMap.get(director) + 1);
                } else {
                    directorMap.put(director, 1);
                }
            }

        }

        for (Map.Entry<String, Integer> entry : directorMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue()+" movie(s)");
        }
    }

    public static void showDirectorsByAverageRate(List<IMDBMovie> filmList) {
        System.out.println("");
        System.out.println("Show directors by average rate");
        Map<String, ArrayList<Float>> scoreMap = new HashMap<String, ArrayList<Float>>();
        for (int i = 0; i < filmList.size(); i++) {
            ArrayList<String> directors = filmList.get(i).directors;
            for (int k = 0; k < directors.size(); k++) {
                String director = directors.get(k);
                if (!scoreMap.containsKey(director)) {
                    scoreMap.put(director, new ArrayList<Float>());
                }

                scoreMap.get(director).add(filmList.get(i).rate);

            }
        }

        List<Director> directorsRateList = new ArrayList<Director>();

        for (Map.Entry<String, ArrayList<Float>> entry : scoreMap.entrySet()) {
            ArrayList<Float> rates = entry.getValue();
            float sumOfAllTheRates = 0;
            for (int l = 0; l < rates.size(); l++) {
                sumOfAllTheRates += rates.get(l);

            }
            float theMostAverageRate = sumOfAllTheRates / rates.size();

            Director dir = new Director();
            dir.name = entry.getKey();
            dir.averageRate = theMostAverageRate;
            directorsRateList.add(dir);
        }

        directorsRateList.sort(((dir1, dir2) -> {
            float diff = dir1.averageRate - dir2.averageRate;
            if (diff < 0) {
                return 1;
            } else if (diff > 0) {
                return -1;
            }
            return 0;
        }));

        for (int d = 0; d < directorsRateList.size(); d++) {
            System.out.println(String.valueOf(d + 1) + ". " + directorsRateList.get(d).name + " - " + directorsRateList.get(d).averageRate);
        }
    }


    public static void showActorsByAverageRate(List<IMDBMovie> filmList) {
        System.out.println("");
        System.out.println("Show actors by average rate");
        Map<String, ArrayList<Float>> scoreMap = new HashMap<String, ArrayList<Float>>();
        for (int i = 0; i < filmList.size(); i++) {
            ArrayList<String> actorList = filmList.get(i).castList;
            for (int k = 0; k < actorList.size(); k++) {
                String actor = actorList.get(k);
                if (!scoreMap.containsKey(actor)) {
                    scoreMap.put(actor, new ArrayList<Float>());
                }

                scoreMap.get(actor).add(filmList.get(i).rate);

            }
        }

        List<Actor> actorsRateList = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Float>> entry : scoreMap.entrySet()) {
            ArrayList<Float> rates = entry.getValue();
            float sumOfAllTheRates = 0;
            for (int l = 0; l < rates.size(); l++) {
                sumOfAllTheRates += rates.get(l);

            }
            float theMostAverageRate = sumOfAllTheRates / rates.size();

            Actor act = new Actor();
            act.name = entry.getKey();
            act.averageRate = theMostAverageRate;
            actorsRateList.add(act);
        }

        actorsRateList.sort(((act1, act2) -> {
            float diff = act1.averageRate - act2.averageRate;
            if (diff < 0) {
                return 1;
            } else if (diff > 0) {
                return -1;
            }
            return 0;
        }));

        for (int d = 0; d < actorsRateList.size(); d++) {
            System.out.println(String.valueOf(d + 1) + ". " + actorsRateList.get(d).name + " - " + actorsRateList.get(d).averageRate);
        }
    }


    public static void showNumberOfMoviesByActor(List<IMDBMovie> filmList) {
        System.out.println("");
        System.out.println("The number of movies by actor");
        Map<String, Integer> actorsMap = new HashMap<String, Integer>();
        for (int i = 0; i < filmList.size(); i++) {
            ArrayList<String> actors = filmList.get(i).castList;
            for (int k = 0; k < actors.size(); k++) {
                String actor = actors.get(k);
                if (actorsMap.containsKey(actor)) {
                    actorsMap.put(actor, actorsMap.get(actor) + 1);
                } else {
                    actorsMap.put(actor, 1);
                }
            }

        }

        for (Map.Entry<String, Integer> entry : actorsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue()+" movie(s)");
        }
    }
}
