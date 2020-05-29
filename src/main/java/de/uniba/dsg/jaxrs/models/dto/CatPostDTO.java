package de.uniba.dsg.jaxrs.models.dto;

import de.uniba.dsg.jaxrs.models.logic.Cat;
import de.uniba.dsg.jaxrs.models.logic.Movie;
import de.uniba.dsg.jaxrs.models.logic.Popularity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "catPostDto")
@XmlType(propOrder = {"id", "popularity", "name", "movies", "imageUrl"})
public class CatPostDTO {

    private Popularity popularity;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private List<MovieDTO> movies;
    @XmlElement(required = true)
    private String imageUrl;

    public CatPostDTO() {

    }

    public Popularity getPopularity() {
        return this.popularity;
    }

    public void setPopularity(final Popularity popularity) {
        this.popularity = popularity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<MovieDTO> getMovies() {
        return this.movies;
    }

    public void setMovies(final List<MovieDTO> movies) {
        this.movies = movies;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Cat unmarshall() {
        final List<Movie> movies = new ArrayList<>();
        for (final MovieDTO m : this.movies) {
            movies.add(m.unmarshall());
        }

        return new Cat(0, this.popularity, this.name, this.imageUrl, movies);
    }

    @Override
    public String toString() {
        return "CatPostDTO{" +
                "popularity=" + this.popularity +
                ", name='" + this.name + '\'' +
                ", movies=" + this.movies +
                ", imageUrl='" + this.imageUrl + '\'' +
                '}';
    }
}
