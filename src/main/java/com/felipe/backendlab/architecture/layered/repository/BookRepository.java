package com.felipe.backendlab.architecture.layered.repository;

import java.util.ArrayList;
import java.util.List;
import com.felipe.backendlab.architecture.layered.entity.Book;

public class BookRepository {

    // Mimicking a database table using an in-memory List
    private final List<Book> books = new ArrayList<>();

    // Save a book in database
    public void save(Book book) {
        books.add(book);
    }

    // Retrieve all books
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }
}
