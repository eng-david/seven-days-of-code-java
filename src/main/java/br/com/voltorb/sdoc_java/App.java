package br.com.voltorb.sdoc_java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

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

        String body = "";
        try {
            URI endereço = URI.create(url.toString());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereço)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            body = response.body();
        } catch (IOException | InterruptedException ex) {
            body = "error";
        }

        System.out.println(body);

    }
}
