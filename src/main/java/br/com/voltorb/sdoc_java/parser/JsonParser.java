package br.com.voltorb.sdoc_java.parser;

import java.util.List;

import br.com.voltorb.sdoc_java.model.Movie;

public interface JsonParser {
    public List<Movie> parse(String json);
}
