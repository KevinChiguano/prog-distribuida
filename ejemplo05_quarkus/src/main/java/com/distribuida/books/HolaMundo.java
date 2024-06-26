package com.distribuida.books;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;

@Path("/hola")
public class HolaMundo {

    @Inject
    @ConfigProperty(name = "app.books.msg", defaultValue = "xx")
    private String message;


    @GET
    public String hola() {

        Config config = ConfigProvider.getConfig();

        /*config.getConfigSources()
                .forEach(t -> System.out.printf("%d: %s \n", t.getOrdinal(), t.getName()));*/

        //var msg = config.getValue("app.books.msg", String.class);

        System.out.println(message);


        return message+ " " + LocalDateTime.now();
    }
}
