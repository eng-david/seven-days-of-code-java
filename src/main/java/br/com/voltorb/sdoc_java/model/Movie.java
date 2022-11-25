package br.com.voltorb.sdoc_java.model;

public record Movie(String title, String imageUrl, String rating, Integer year) implements RatedContent, Comparable<RatedContent>{

    @Override
    public int compareTo(RatedContent another) {
        return this.year.compareTo(another.year());
    }
    
}
