package br.com.voltorb.sdoc_java;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.voltorb.sdoc_java.client.ApiClient;
import br.com.voltorb.sdoc_java.client.ArchiveApiClient;
import br.com.voltorb.sdoc_java.client.MarvelApiClient;
import br.com.voltorb.sdoc_java.client.TmdbApiClient;
import br.com.voltorb.sdoc_java.parser.JsonParser;
import br.com.voltorb.sdoc_java.parser.MarvelJsonParser;
import br.com.voltorb.sdoc_java.parser.TmdbJsonParser;

public enum API_EXTRATOR {

    TMDB("tmdb_secret.txt", new TmdbApiClient(), new TmdbJsonParser()),
    ARCHIVE("tmdb_secret.txt", new ArchiveApiClient(), new TmdbJsonParser()),
    MARVEL("marvel_secret.txt", new MarvelApiClient(), new MarvelJsonParser());

    private ApiClient apiClient;
    private JsonParser jsonParser;

    API_EXTRATOR(String secretPath, ApiClient apiClient, JsonParser jsonParser) {
        this.apiClient = apiClient;
        this.jsonParser = jsonParser;

        this.apiClient.setApiKey(getKeyFromFile(secretPath));
    }

    private Map<String, String> getKeyFromFile(String filePath) {
        Map<String, String> key = new HashMap<>();
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(filePath));
            while (scanner.hasNextLine()) {
                key.put(scanner.nextLine(), scanner.nextLine());
            }
            scanner.close();
            return key;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public JsonParser getJsonParser() {
        return jsonParser;
    }

}
