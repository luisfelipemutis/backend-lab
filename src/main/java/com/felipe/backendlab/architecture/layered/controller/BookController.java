package com.felipe.backendlab.architecture.layered.controller;

import java.util.Scanner;
import java.util.List;
import com.felipe.backendlab.architecture.layered.entity.Book;

import com.felipe.backendlab.architecture.layered.service.BookService;

public class BookController {

    // The controller requires the service layer
    private final BookService bookService = new BookService();
    private final Scanner sc = new Scanner(System.in);

    public void startApplication() {
        System.out.println("--- Welcome to the library application! ---");

        while (true) {
            System.out.println("\n1. Add a book\n2. View all books\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear the newline character

            switch (choice) {
                case 1 -> handleAddBook();
                case 2 -> handleViewBooks();
                case 3 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void handleAddBook() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter book author: ");
        String author = sc.nextLine();

        // Send user input to the Service layer and capture the result
        String outputMessage = bookService.addBook(id, title, author);
        System.out.println(outputMessage);
    }

    private void handleViewBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("\n--- Current inventory ---");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}
