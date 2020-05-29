package de.uniba.dsg.jaxrs.models.api;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

@XmlRootElement
@XmlType(propOrder = {"page", "noOfPages", "first", "previous", "next", "last"})
public class Pagination {

    private int page;
    private int noOfPages;

    private URI first;
    private URI previous;
    private URI next;
    private URI last;

    public Pagination() {

    }

    public Pagination(final URI path, final int currentPage, final int pageLimit, final int collectionSize) {
        this.page = currentPage;
        this.noOfPages = (int) Math.ceil((double) collectionSize / (double) pageLimit);

        this.first = UriBuilder.fromUri(path).queryParam("page", 1).queryParam("pageLimit", pageLimit).build();
        this.last = UriBuilder.fromUri(path).queryParam("page", this.noOfPages).queryParam("pageLimit", pageLimit).build();
        this.previous = UriBuilder.fromUri(path).queryParam("page", Math.max((this.page - 1), 1)).queryParam("pageLimit", pageLimit).build();
        this.next = UriBuilder.fromUri(path).queryParam("page", Math.min((this.page + 1), this.noOfPages)).queryParam("pageLimit", pageLimit).build();
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public int getNoOfPages() {
        return this.noOfPages;
    }

    public void setNoOfPages(final int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public URI getFirst() {
        return this.first;
    }

    public void setFirst(final URI first) {
        this.first = first;
    }

    public URI getPrevious() {
        return this.previous;
    }

    public void setPrevious(final URI previous) {
        this.previous = previous;
    }

    public URI getNext() {
        return this.next;
    }

    public void setNext(final URI next) {
        this.next = next;
    }

    public URI getLast() {
        return this.last;
    }

    public void setLast(final URI last) {
        this.last = last;
    }
}
