package br.com.voltorb.sdoc_java.parser;

import java.util.List;

import br.com.voltorb.sdoc_java.model.RatedContent;

public interface JsonParser {
    public List<? extends RatedContent> parse();
}
