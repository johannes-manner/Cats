package de.uniba.dsg.jaxrs.resources;

import de.uniba.dsg.jaxrs.controller.CatsService;
import de.uniba.dsg.jaxrs.models.dto.MovieDTO;
import de.uniba.dsg.jaxrs.models.error.ErrorMessage;
import de.uniba.dsg.jaxrs.models.error.ErrorType;
import de.uniba.dsg.jaxrs.models.logic.Movie;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.logging.Logger;

@Path("movies")
public class MovieResource {

    private static final Logger logger = Logger.getLogger("MovieResource");

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getFamousMovies(@Context final UriInfo uriInfo) {
        logger.info("Get all movies");
        // generic entity is necessary here, since there is no default message writer for array lists
        final GenericEntity<List<MovieDTO>> entity = new GenericEntity<List<MovieDTO>>(MovieDTO.marshall(CatsService.instance.getFamousMovies(), uriInfo.getBaseUri())) {
        };
        final Response build = Response.ok(entity).build();
        return build;
    }

    @GET
    @Path("{movieId}")
    public Response getFamousMovie(@Context final UriInfo uriInfo, @PathParam("movieId") final int movieId) {
        logger.info("Get Movie with Id: " + movieId);
        final Movie m = CatsService.instance.getFamousMovieById(movieId);
        if (m == null) {
            logger.info("Movie not found: " + movieId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new MovieDTO(m, uriInfo.getBaseUri())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMovie(final MovieDTO movieDTO, @Context final UriInfo uriInfo) {
        logger.info("Create a new movie in DB: " + movieDTO);
        if (movieDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(ErrorType.INVALID_PARAMETER, "Body was empty")).build();
        }
        final Movie newMovie = movieDTO.unmarshall();
        // generate id and other stuff
        final Movie movie = CatsService.instance.addMovie(newMovie);


        return Response.created(UriBuilder.fromUri(uriInfo.getBaseUri()).path(MovieResource.class).path(MovieResource.class, "getFamousMovie").build(movie.getMovieId())).build();

    }
}
