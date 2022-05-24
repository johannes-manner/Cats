package de.uniba.dsg.jaxrs.resources;

import de.uniba.dsg.jaxrs.models.api.Pagination;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

public class PaginationHelper<T> {

    private final List<T> items;
    private int startIndex;
    private int endIndex;

    public PaginationHelper(final List<T> items) {
        this.startIndex = 0;
        this.endIndex = 0;
        this.items = items;
    }

    public Pagination getPagination(final UriInfo info, int page, final int pageLimit) {
        // pagination
        final int catSize = this.items.size();
        this.startIndex = (page - 1) * pageLimit;
        if (this.startIndex > catSize) {
            // error message or startIndex = 0 , dependent on your implementation guidelines (for retrieving information this policy should be reasonable)
            this.startIndex = 0;
            page = 1;
        }
        this.endIndex = this.startIndex + pageLimit;
        if (this.endIndex > catSize) {
            this.endIndex = catSize;
        }

        return new Pagination(info.getAbsolutePath(), page, pageLimit, catSize);
    }

    public List<T> getPaginatedList() {
        return this.items.subList(this.startIndex, this.endIndex);
    }
}
