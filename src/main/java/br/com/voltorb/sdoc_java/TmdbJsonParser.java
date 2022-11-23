package br.com.voltorb.sdoc_java;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TmdbJsonParser {
    
    private String json;

    public TmdbJsonParser(String json) {
        this.json = json;
    }

    public List<Movie> parse(){
        // --- Generate Movies List ---
        String[] movies = moviesList(json);

        // --- Generate Atributes List ---
        return moviesAtributesList(movies);
    }

    private static String[] moviesList(String json) {
        Pattern REGEX_ITEMS = Pattern.compile("\\[(.+)\\]");
        Matcher list = REGEX_ITEMS.matcher(json);
        if (!list.find())
            throw new IllegalArgumentException("Não encontrou items.");
        return list.group(1).split("\\},\\{");
    }

    private static List<Movie> moviesAtributesList(String[] movies) {

        List<Movie> atributes = new ArrayList<>();

        Pattern REGEX_GET_TITLE = Pattern.compile("\"(title)\":\"(.*?)\"");
        Pattern REGEX_GET_IMAGE_URL = Pattern.compile("\"(poster_path)\":\"(.*?)\"");
        Pattern REGEX_GET_RATING = Pattern.compile("\"(vote_average)\":(.*?),");
        Pattern REGEX_GET_YEAR = Pattern.compile("\"(release_date)\":\"(.*?)-");

        for (String movie : movies) {
            Matcher matcherTitle = REGEX_GET_TITLE.matcher(movie);
            Matcher matcherImageUrl = REGEX_GET_IMAGE_URL.matcher(movie);
            Matcher matcherRating = REGEX_GET_RATING.matcher(movie);
            Matcher matcherYear = REGEX_GET_YEAR.matcher(movie);

            while (matcherTitle.find() && matcherImageUrl.find() && matcherRating.find() && matcherYear.find())
                atributes.add(
                        new Movie(
                                matcherTitle.group(2),
                                matcherImageUrl.group(2),
                                matcherRating.group(2),
                                Integer.parseInt(matcherYear.group(2))));
        }

        return atributes;
    }
}
