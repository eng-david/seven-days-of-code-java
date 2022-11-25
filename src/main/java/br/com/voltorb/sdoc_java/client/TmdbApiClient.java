package br.com.voltorb.sdoc_java.client;

import java.util.Map;

public class TmdbApiClient extends ApiClient_I {

    @Override
    public void setApiKey(Map<String, String> key) {
        this.apiKey = key;
        
        url.append("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1&");
        url.append(apiKey.get("private"));
    }

}