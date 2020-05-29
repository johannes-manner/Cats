package de.uniba.dsg.jaxrs.models.api;

import de.uniba.dsg.jaxrs.models.dto.CatDTO;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"pagination", "cats", "href"})
public class PaginatedCats {
    private Pagination pagination;
    private List<CatDTO> cats;

    private URI href;

    public PaginatedCats() {

    }

    public PaginatedCats(final Pagination pagination, final List<CatDTO> cats, final URI href) {
        this.pagination = pagination;
        this.cats = cats;
        this.href = href;
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public void setPagination(final Pagination pagination) {
        this.pagination = pagination;
    }

    public List<CatDTO> getCats() {
        return this.cats;
    }

    public void setCats(final List<CatDTO> cats) {
        this.cats = cats;
    }

    public URI getHref() {
        return this.href;
    }

    public void setHref(final URI href) {
        this.href = href;
    }
}
