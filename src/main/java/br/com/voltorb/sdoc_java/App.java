package br.com.voltorb.sdoc_java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {

        // --- Build URL string with API key ---
        Path path = Paths.get("secret.txt");
        StringBuilder url = new StringBuilder("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1&");
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine()) {
                url.append(scanner.nextLine());
                System.out.println(url);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Get API Json ---
        //String json = fetchJson(url);

        // --- Get Json from file ---
        String json = fetchJsonFromFile(Paths.get("json.txt"));

        // --- Generate Movies List ---
        String[] movies = moviesList(json);

        // --- Generate Atributes List ---
        List<Movie> atributes = moviesAtributesList(movies);

        // --- Print Title & URL atributes from eath movie
        for (Movie movie : atributes) {
            System.out.println("title: " + movie.title());
            System.out.println("image url: " + movie.imageUrl());
            System.out.println("rating: " + movie.rating());
            System.out.println("year: " + movie.year() + "\n");
        }

    }

    static String[] moviesList(String json) {
        Pattern REGEX_ITEMS = Pattern.compile("\\[(.+)\\]");
        Matcher list = REGEX_ITEMS.matcher(json);
        if (!list.find())
            throw new IllegalArgumentException("Não encontrou items.");
        return list.group(1).split("\\},\\{");
    }

    static List<Movie> moviesAtributesList(String[] movies) {

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
            
            while(matcherTitle.find() && matcherImageUrl.find() && matcherRating.find() && matcherYear.find())
            atributes.add(
                new Movie(
                    matcherTitle.group(2), 
                    matcherImageUrl.group(2), 
                    matcherRating.group(2), 
                    Integer.parseInt(matcherYear.group(2))));
        }

        return atributes;
    }

    static String fetchJson(StringBuilder url) {
        try {
            URI endereço = URI.create(url.toString());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereço)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request,
                    BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException ex) {
            return "error";
        }
    }

    static String fetchJsonFromFile(Path path) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine())
                sb.append(scanner.nextLine());
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
