package de.uniba.dsg.jaxrs;

import de.uniba.dsg.jaxrs.provider.CatMessageBodyWriter;
import de.uniba.dsg.jaxrs.resources.CatResource;
import de.uniba.dsg.jaxrs.resources.MovieResource;
import de.uniba.dsg.jaxrs.resources.SwaggerUI;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExamplesApi extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();
        // resources
        resources.add(CatResource.class);
        resources.add(MovieResource.class);
        // swagger-ui
        resources.add(SwaggerUI.class);
        // providers
        resources.add(CatMessageBodyWriter.class);

        return resources;
    }
}
