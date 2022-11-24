package br.com.voltorb.sdoc_java.model;

public record Movie(String title, String imageUrl, String rating, Integer year) implements RatedContent{
    
}
