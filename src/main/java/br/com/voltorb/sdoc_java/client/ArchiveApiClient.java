package br.com.voltorb.sdoc_java.client;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ArchiveApiClient extends ApiClient_I {

    @Override
    public String getBody() {
        return fetchJsonFromFile(Paths.get("json.txt"));
    }

    private String fetchJsonFromFile(Path path) {
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

    @Override
    public String getUrl() {
        return null;
    }

}