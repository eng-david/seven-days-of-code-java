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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {

        // --- Build URL string with API key ---
        Path path = Paths.get("secret.txt");
        StringBuilder url = new
        StringBuilder("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1&");
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
        String json = "";
        try {
        URI endereço = URI.create(url.toString());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereço)
        .GET()
        .build();
        HttpResponse<String> response = client.send(request,
        BodyHandlers.ofString());
        json = response.body();
        } catch (IOException | InterruptedException ex) {
        json = "error";
        }

        // // --- Get Json from file ---
        // Path path = Paths.get("json.txt");
        // StringBuilder sb = new StringBuilder();
        // try {
        //     Scanner scanner = new Scanner(path);
        //     while (scanner.hasNextLine())
        //         sb.append(scanner.nextLine());
        //     scanner.close();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // String json = sb.toString();

        // --- Generate Movies List ---
        Pattern REGEX_ITEMS = Pattern.compile("\\[(.+)\\]");
        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find())
            throw new IllegalArgumentException("Não encontrou items.");
        String[] items = matcher.group(1).split("\\},\\{");

        // --- Generate Atributes List ---
        Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"?\\[?(.*?)\\]?\"?,");
        List<Map<String, String>> dados = new ArrayList<>();
        for (String item : items) {
            Map<String, String> atributosItem = new HashMap<>();
            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }
            dados.add(atributosItem);
        }

        // --- Print Title & url atributes from eath movie
        for (Map<String, String> dado : dados) {
            System.out.println("title: " + dado.get("title"));
            System.out.println("image url: " + dado.get("poster_path") + "\n");
        }

    }
}
