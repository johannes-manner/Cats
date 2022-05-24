package de.uniba.dsg.jaxrs.resources;

import de.uniba.dsg.jaxrs.controller.CatsService;
import de.uniba.dsg.jaxrs.models.api.PaginatedCats;
import de.uniba.dsg.jaxrs.models.dto.CatDTO;
import de.uniba.dsg.jaxrs.models.dto.CatPostDTO;
import de.uniba.dsg.jaxrs.models.error.ErrorMessage;
import de.uniba.dsg.jaxrs.models.error.ErrorType;
import de.uniba.dsg.jaxrs.models.logic.Cat;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.logging.Logger;

@Path("cats")
public class CatResource {

    private static final Logger logger = Logger.getLogger("CatResource");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCats(@Context final UriInfo info,
                            @QueryParam("pageLimit") @DefaultValue("10") final int pageLimit,
                            @QueryParam("page") @DefaultValue("1") final int page) {
        logger.info("Get all cats. Pagination parameter: page-" + page + " pageLimit-" + pageLimit);

        // Parameter validation
        if (pageLimit < 1 || page < 1) {
            final ErrorMessage errorMessage = new ErrorMessage(ErrorType.INVALID_PARAMETER, "PageLimit or page is less than 1. Read the documentation for a proper handling!");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
        }

        final PaginationHelper<Cat> helper = new PaginationHelper<>(CatsService.instance.getFamousCats());
        final PaginatedCats response = new PaginatedCats(helper.getPagination(info, page, pageLimit), CatDTO.marshall(helper.getPaginatedList(), info.getBaseUri()), info.getRequestUri());
        return Response.ok(response).build();
    }

    @GET
    @Path("{catId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getCat(@Context final UriInfo info, @PathParam("catId") final int id) {
        logger.info("Get cat with id " + id);
        final Cat cat = CatsService.instance.getFamousCat(id);

        if (cat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new CatDTO(cat, info.getBaseUri())).build();
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_XML)
    public Response searchForCats(@Context final UriInfo info, @QueryParam("movie") final String movie) {
        logger.info("SEARCH: cats in movie " + movie);
        return Response.ok(new GenericEntity<List<CatDTO>>(CatDTO.marshall(CatsService.instance.getFamousCats(movie), info.getBaseUri())) {
        }).build();
    }

    @POST
    public Response createCat(final CatPostDTO newCat, @Context final UriInfo uriInfo) {
        logger.info("Create cat " + newCat);
        if (newCat == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(ErrorType.INVALID_PARAMETER, "Body was empty")).build();
        }
        final Cat cat = newCat.unmarshall();

        CatsService.instance.addCat(cat);

        return Response.created(UriBuilder.fromUri(uriInfo.getBaseUri()).path(CatResource.class).path(CatResource.class, "getCat").build(cat.getId())).build();
    }

    @PUT
    @Path("{cat-id}")
    public Response updateCat(@Context final UriInfo uriInfo, @PathParam("cat-id") final int id, final CatPostDTO updatedCat) {
        logger.info("Update cat " + updatedCat);
        if (updatedCat == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(ErrorType.INVALID_PARAMETER, "Body was empty")).build();
        }

        final Cat cat = CatsService.instance.getFamousCat(id);

        if (cat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        final Cat resultCat = CatsService.instance.updateCat(id, updatedCat.unmarshall());

        return Response.ok().entity(new CatDTO(resultCat, uriInfo.getBaseUri())).build();
    }

    @DELETE
    @Path("{catId}")
    public Response deleteSpecificCat(@PathParam("catId") final int id) {
        logger.info("Delete cat with id " + id);
        final boolean deleted = CatsService.instance.deleteCat(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().build();
        }
    }
}
