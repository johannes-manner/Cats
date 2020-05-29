package de.uniba.dsg.jaxrs.models.dto;

import de.uniba.dsg.jaxrs.models.logic.Cat;
import de.uniba.dsg.jaxrs.models.logic.Popularity;
import de.uniba.dsg.jaxrs.resources.CatResource;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * DTOs are necessary to hide the inner implementation of your model.
 * You probably change your <@{@link Cat} class but this has no effect on your exposed API (non breaking change).
 * If you have no DTO object, you have to redeploy the API and change the version number (this will annoy your customers).
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cat")
@XmlType(propOrder = {"id", "popularity", "name", "movies", "imageUrl", "href"})
public class CatDTO {

    private int id;
    private Popularity popularity;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private List<MovieShortDTO> movies;
    @XmlElement(required = true)
    private String imageUrl;

    private URI href;

    public CatDTO() {

    }

    public CatDTO(final Cat cat, final URI baseUri) {
        this.id = cat.getId();
        this.popularity = cat.getPopularity();
        this.name = cat.getName();
        this.movies = new ArrayList<>();
        this.movies = MovieShortDTO.marshall(cat.getMovies(), baseUri);
        this.imageUrl = cat.getImageUrl();
        this.href = UriBuilder.fromUri(baseUri).path(CatResource.class).path(CatResource.class, "getCat").build(this.id);
    }

    public static List<CatDTO> marshall(final List<Cat> catList, final URI baseUri) {
        final ArrayList<CatDTO> cats = new ArrayList<>();
        for (final Cat cat : catList) {
            cats.add(new CatDTO(cat, baseUri));
        }
        return cats;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
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

    public List<MovieShortDTO> getMovies() {
        return this.movies;
    }

    public void setMovies(final List<MovieShortDTO> movies) {
        this.movies = movies;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public URI getHref() {
        return this.href;
    }

    public void setHref(final URI href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "CatDTO{" +
                "id=" + this.id +
                ", popularity=" + this.popularity +
                ", name='" + this.name + '\'' +
                ", movies=" + this.movies +
                ", imageUrl='" + this.imageUrl + '\'' +
                ", href=" + this.href +
                '}';
    }
}
