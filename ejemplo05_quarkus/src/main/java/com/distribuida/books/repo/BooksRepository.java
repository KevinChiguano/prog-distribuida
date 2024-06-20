package com.distribuida.books.repo;

import com.distribuida.books.db.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
//<Entity y clave primaria tipo>
public class BooksRepository implements PanacheRepositoryBase<Book, Integer> {


}
