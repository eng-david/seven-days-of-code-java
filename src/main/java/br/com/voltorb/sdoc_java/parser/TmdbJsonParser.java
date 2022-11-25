package br.com.voltorb.sdoc_java.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.voltorb.sdoc_java.model.Movie;

public class TmdbJsonParser implements JsonParser {

    @Override
    public List<Movie> parse(String json) {
        // --- Generate Movies List ---
        List<String> movies = moviesList(json);

        // --- Generate Atributes List ---
        return moviesAtributesList(movies);
    }

    private List<String> moviesList(String json) {
        Pattern REGEX_ITEMS = Pattern.compile("\\[(.+)\\]");
        Matcher list = REGEX_ITEMS.matcher(json);
        if (!list.find())
            throw new IllegalArgumentException("Não encontrou items.");

        return Arrays.asList(list.group(1).split("\\},\\{"));
    }

    private List<Movie> moviesAtributesList(List<String> movies) {

        List<Movie> moviesList = new ArrayList<>();

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
                moviesList.add(
                        new Movie(
                                matcherTitle.group(2),
                                ("https://image.tmdb.org/t/p/w200" + matcherImageUrl.group(2)),
                                matcherRating.group(2),
                                Integer.parseInt(matcherYear.group(2))));
        }

        return moviesList;
    }
}
