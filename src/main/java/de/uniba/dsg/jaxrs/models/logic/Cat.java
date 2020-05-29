package de.uniba.dsg.jaxrs.models.logic;

import java.util.List;

public class Cat {
    private int id;
    private Popularity popularity;
    private String name;
    private List<Movie> movies;
    private String imageUrl;

    public Cat() {
    }

    public Cat(final int id, final Popularity popularity, final String name, final String imageUrl, final List<Movie> movies) {
        this.id = id;
        this.popularity = popularity;
        this.name = name;
        this.imageUrl = imageUrl;
        this.movies = movies;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Popularity getPopularity() {
        return this.popularity;
    }

    public void setPopularity(final Popularity popularity) {
        this.popularity = popularity;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(final List<Movie> movies) {
        this.movies = movies;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
