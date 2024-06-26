package com.distribuida.books.clients;

import com.distribuida.books.db.Book;
import com.distribuida.books.dtos.AuthorDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "http://localhost:9090")
public interface AuthorRestClient {

    //@GET
    //public List<Book> findAll();

    @GET
    @Path("/{id}")
    AuthorDto findById(@PathParam("id")Integer id);


    }
