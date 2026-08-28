package com.felipe.backendlab.architecture.layered.service;

import com.felipe.backendlab.architecture.layered.repository.BookRepository;
import com.felipe.backendlab.architecture.layered.entity.Book;
import java.util.List;

public class BookService {

    // The service requires the repository to do its job
    private final BookRepository bookrepository = new BookRepository();

    public String addBook(String id, String title, String author) {
        // Business Rule 1: Title and Author cannot be empty
        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            return "Error: Title and Author cannot be empty.";
        }

        // Business Rule 2: No duplicate titles allowed
        List<Book> existingBooks = bookrepository.findAll();
        for (Book book : existingBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return "Error: A book with the title '" + title + "' already exists.";
            }
        }

        // If rules pass, create the object and save it
        Book book = new Book(id, title, author);
        bookrepository.save(book);
        return "Book added successfully: " + book.getTitle();
    }

    // Services can pass data straight down if no business logic is required
    public List<Book> getAllBooks() {
        return bookrepository.findAll();
    }
}
