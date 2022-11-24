package br.com.voltorb.sdoc_java.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class MarvelApiClient extends ApiClient_I {

    public MarvelApiClient(Map<String, String> key) throws NoSuchAlgorithmException {
        this.apiKey = key;

        url.append("https://gateway.marvel.com:443/v1/public/series?");
        String ts = "1";
        String hash = generateMd5(ts + apiKey.get("private") + apiKey.get("public"));
        url.append("apikey=" + apiKey.get("public"));
        url.append("&ts=" + ts);
        url.append("&hash=" + hash);
    }

    private String generateMd5(String secret) {

        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(secret.getBytes());
            byte[] digest = md.digest();
            for (byte b : digest)
                hash.append(String.format("%02x", b & 0xff));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

}