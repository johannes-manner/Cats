package de.uniba.dsg.jaxrs.models.dto;

import de.uniba.dsg.jaxrs.models.logic.Movie;
import de.uniba.dsg.jaxrs.resources.MovieResource;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * DTOs are necessary to hide the inner implementation of your model.
 * You probably change your <@{@link de.uniba.dsg.jaxrs.models.logic.Movie} class but this has no effect on your exposed API (non breaking change).
 * If you have no DTO object, you have to redeploy the API and change the version number (this will annoy your customers).
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "movie")
@XmlType(propOrder = {"movieId", "name", "year", "href"})
public class MovieDTO {

    private int movieId;
    @XmlElement(required = true)
    private String name;
    private int year;

    private URI href;

    public MovieDTO() {

    }

    public MovieDTO(final Movie m, final URI baseUri) {
        this.movieId = m.getMovieId();
        this.name = m.getName();
        this.year = m.getYear();

        this.href = UriBuilder.fromUri(baseUri).path(MovieResource.class).path(MovieResource.class, "getFamousMovie").build(this.movieId);
    }

    public static List<MovieDTO> marshall(final List<Movie> movieList, final URI baseUri) {
        final ArrayList<MovieDTO> movies = new ArrayList<>();
        for (final Movie m : movieList) {
            movies.add(new MovieDTO(m, baseUri));
        }
        return movies;
    }

    public int getMovieId() {
        return this.movieId;
    }

    public void setMovieId(final int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public URI getHref() {
        return this.href;
    }

    public void setHref(final URI href) {
        this.href = href;
    }

    public Movie unmarshall() {
        return new Movie(this.movieId, this.name, this.year);
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "movieId=" + this.movieId +
                ", name='" + this.name + '\'' +
                ", year=" + this.year +
                ", href=" + this.href +
                '}';
    }
}
