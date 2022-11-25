package br.com.voltorb.sdoc_java;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.voltorb.sdoc_java.client.ApiClient;
import br.com.voltorb.sdoc_java.model.Movie;
import br.com.voltorb.sdoc_java.parser.JsonParser;

public class App {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        // --- Content Origin ---
        // choose de content just setting this line, options: ARCHIVE, TMDB, MARVEL
        API_EXTRATOR myApi = API_EXTRATOR.ARCHIVE;
        ApiClient apiClient = myApi.getApiClient();
        JsonParser jsonParser = myApi.getJsonParser();

        // --- Get Json from API ---
        String json = apiClient.getBody();

        // --- Generate Movies List ---
        List<Movie> movies = jsonParser.parse(json).stream().sorted().toList();

        // --- Print Title & URL atributes from eath movie ---
        printMovies(movies);

        // --- Generate HTML file ---
        FileWriter writer = new FileWriter("page.html");
        new HTMLGenerator(writer).generate(movies);
        writer.close();
    }

    private static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println("title: " + movie.title());
            System.out.println("image url: " + movie.imageUrl());
            System.out.println("rating: " + movie.rating());
            System.out.println("year: " + movie.year() + "\n");
        }
    }
}
