package br.com.voltorb.sdoc_java.client;

import java.util.Map;

public class ApiClient_I implements ApiClient {

    protected Map<String, String> apiKey;
    protected StringBuilder url = new StringBuilder();

    @Override
    public void setApiKey(Map<String, String> apiKey) {
        this.apiKey = apiKey;        
    }

    @Override
    public String getUrl() {
        return this.url.toString();
    }

    @Override
    public String getBody() {
        return new HTTPClient().getBody(this.url.toString());
    }
    
}
