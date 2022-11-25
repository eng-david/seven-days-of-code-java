package br.com.voltorb.sdoc_java.client;

import java.util.Map;

public interface ApiClient {    
    public void setApiKey(Map<String, String> apiKey);
    public String getUrl();
    public String getBody();
}
