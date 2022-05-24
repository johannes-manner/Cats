package de.uniba.dsg.jaxrs.resources;

import de.uniba.dsg.jaxrs.Configuration;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;


@Path("")
public class SwaggerUI {
    private static final Properties properties = Configuration.loadProperties();
    private static final String SWAGGER_UI_FILES = "META-INF/resources/webjars/swagger-ui/" + properties.getProperty("swaggeruiversion");
    private static final String OPEN_API_FILE_NAME = "openapi.yaml";
    private static final Logger logger = Logger.getLogger("SwaggerUI");

    @GET
    @Path("swagger-ui/{file}")
    public Response showSwaggerUI(@PathParam("file") final String file) {
        String resourcePath;
        if (file.equals(OPEN_API_FILE_NAME)) {
            resourcePath = file;
        } else {
            resourcePath = String.format("%s/%s", SWAGGER_UI_FILES, file);
        }
        InputStream resource = getClass().getClassLoader().getResourceAsStream(resourcePath);
        return Objects.isNull(resource)
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok().entity(resource).build();
    }

}
