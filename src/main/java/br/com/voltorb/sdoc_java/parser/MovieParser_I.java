package br.com.voltorb.sdoc_java.parser;

import java.util.List;

import br.com.voltorb.sdoc_java.model.Movie;

public class MovieParser_I implements JsonParser{

    protected String json;

    @Override
    public List<Movie> parse() {
        // --- Generate Movies List ---
        List<String> movies = moviesList(json);

        // --- Generate Atributes List ---
        return moviesAtributesList(movies);
    }

    protected List<String> moviesList(String json) {
        return null;
    }

    protected List<Movie> moviesAtributesList(List<String> movies) {
        return null;
    }
    
}
