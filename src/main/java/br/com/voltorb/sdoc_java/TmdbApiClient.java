package br.com.voltorb.sdoc_java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class TmdbApiClient {

    private String apiKey;
    private StringBuilder url = new StringBuilder(
            "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1&");

    public TmdbApiClient(String key) {
        this.apiKey = key;
        url.append(apiKey);
    }

    private String getUrl() {
        return url.toString();
    }

    public String getBody() {
        // --- Get API Json ---
        try {
            URI endereço = URI.create(getUrl());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereço)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request,
                    BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException ex) {
            return "connection error";
        }
    }

}