package br.com.voltorb.sdoc_java;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.com.voltorb.sdoc_java.client.MarvelApiClient;
import br.com.voltorb.sdoc_java.model.Movie;
import br.com.voltorb.sdoc_java.parser.MarvelJsonParser;

public class App {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        
        // --- Read API key from file ---
        Scanner scanner = new Scanner(Paths.get("marvel_secret.txt"));
        Map<String, String> key = new HashMap<>();
        while (scanner.hasNextLine()){
            key.put(scanner.nextLine(), scanner.nextLine());
        }
        scanner.close();

        // --- Get Json from Marvel ---
        String json = new MarvelApiClient(key).getBody();

        // --- Generate Marvel Atributes List ---
        List<Movie> movies = new MarvelJsonParser(json).parse();

        // --- Get Json from TMDB ---
        //String json = new TmdbApiClient(key).getBody();

        // --- Generate TMDB Atributes List ---
        //List<Movie> movies = new TmdbJsonParser(json).parse();

        // --- Get Json from file ---
        //String json = fetchJsonFromFile(Paths.get("json.txt"));
        
        // --- Print Title & URL atributes from eath movie ---
        for (Movie movie : movies) {
            System.out.println("title: " + movie.title());
            System.out.println("image url: " + movie.imageUrl());
            System.out.println("rating: " + movie.rating());
            System.out.println("year: " + movie.year() + "\n");
        }

        // --- Generate HTML file ---
        FileWriter writer = new FileWriter("page.html");
        new HTMLGenerator(writer).generate(movies);
        writer.close();
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
