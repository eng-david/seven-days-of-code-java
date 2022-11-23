package br.com.voltorb.sdoc_java;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {

        // --- Read API key from file
        Scanner scanner = new Scanner(Paths.get("secret.txt"));
        String key = scanner.nextLine();
        scanner.close();
        
        // --- Get Json from TMDB
        String json = new TmdbApiClient(key).getBody();

        // --- Get Json from file ---
        // String json = fetchJsonFromFile(Paths.get("json.txt"));

        // --- Generate Atributes List ---
        List<Movie> movies = new TmdbJsonParser(json).parse();

        // --- Print Title & URL atributes from eath movie
        for (Movie movie : movies) {
            System.out.println("title: " + movie.title());
            System.out.println("image url: " + movie.imageUrl());
            System.out.println("rating: " + movie.rating());
            System.out.println("year: " + movie.year() + "\n");
        }

        // --- Generate HTML file ---
        FileWriter writer = new FileWriter("page.html");
        HTMLGenerator htmlGenerator = new HTMLGenerator(writer);
        htmlGenerator.generate(movies);
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
