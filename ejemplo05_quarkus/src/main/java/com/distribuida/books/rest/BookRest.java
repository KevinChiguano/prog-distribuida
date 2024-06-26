package com.distribuida.books.rest;

import com.distribuida.books.clients.AuthorRestClient;
import com.distribuida.books.db.Book;
import com.distribuida.books.dtos.BookDto;
import com.distribuida.books.repo.BooksRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional //Para no poner el begin ni commit??
public class BookRest {
    @Inject
    BooksRepository repo;

    @Inject
    @RestClient
    AuthorRestClient authorRest;

    @GET
    public List<BookDto> findAll(){

        var books = repo.listAll();

        return books.stream()
                .map(book ->{
                    //buscar el autor obj.getAuthorId()
                    //conectarse al servicio "app-authors"
                    // http://localhost:9090/authors

                    var author = authorRest.findById(book.getAuthorId());
                    BookDto dto = new BookDto();

                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setIsbn(book.getIsbn());
                    dto.setPrice(book.getPrice());
                    dto.setAuthorName(author.getFirstName());
                    dto.setAuthorId(author.getId());

                    return dto;
                    //dto.setAuthorName(.....)

                }).toList();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id){
        var op = repo.findByIdOptional(id);

        if(op.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(op.get()).build();
    }

    @POST
    public Response create(Book book){
        book.setId(null);
        repo.persist(book);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, Book book){
        var obj = repo.findById(id);
        obj.setIsbn(book.getIsbn());
        obj.setTitle(book.getTitle());
        obj.setAuthorId(book.getAuthorId());
        obj.setPrice(book.getPrice());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id){
        repo.deleteById(id);
        return Response.ok().build();
    }

}
