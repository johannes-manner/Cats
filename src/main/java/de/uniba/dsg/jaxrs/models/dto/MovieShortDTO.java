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
 * You probably change your <@{@link Movie} class but this has no effect on your exposed API (non breaking change).
 * If you have no DTO object, you have to redeploy the API and change the version number (this will annoy your customers).n
 * <p>
 * This class is needed to get a short description of a movie for returning this "view" on the data when
 * we call other resources as the specific movie resource. (As a summary within cats.)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "movie")
@XmlType(propOrder = {"name", "href"})
public class MovieShortDTO {

    @XmlElement(required = true)
    private String name;
    private URI href;

    public MovieShortDTO() {

    }

    public MovieShortDTO(final Movie m, final URI baseUri) {
        this.name = m.getName();

        this.href = UriBuilder.fromUri(baseUri).path(MovieResource.class).path(MovieResource.class, "getFamousMovie").build(m.getMovieId());
    }

    public static List<MovieShortDTO> marshall(final List<Movie> movieList, final URI baseUri) {
        final ArrayList<MovieShortDTO> movies = new ArrayList<>();
        for (final Movie m : movieList) {
            movies.add(new MovieShortDTO(m, baseUri));
        }
        return movies;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public URI getHref() {
        return this.href;
    }

    public void setHref(final URI href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "MovieShortDTO{" +
                "name='" + this.name + '\'' +
                ", href=" + this.href +
                '}';
    }
}
