package de.uniba.dsg.jaxrs.resources;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import de.uniba.dsg.jaxrs.Configuration;

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
