package br.com.voltorb.sdoc_java.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

public class ApiClient_I implements ApiClient {

    protected Map<String, String> apiKey;
    protected StringBuilder url = new StringBuilder();

    @Override
    public String getBody() {
        // --- Get API Json ---
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
            return "connection error";
        }
    }
    
}
