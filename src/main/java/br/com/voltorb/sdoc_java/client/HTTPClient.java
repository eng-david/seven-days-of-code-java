package br.com.voltorb.sdoc_java.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HTTPClient {
    public String getBody(String url) {
        // --- Get API Json ---
        try {
            URI uri = URI.create(url.toString());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(uri)
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
