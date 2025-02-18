package com.example.books_store.model;

import com.example.books_store.observer.Observer;
import com.example.books_store.service.FileGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FileLoggerObserver implements Observer {

    private final FileGateway fileGateway;
    @Override
    public void update(Book book, String action) {
        String message = "Book " + action + ": \'" + book.getTitle() + "\' by " + book.getAuthor();
        fileGateway.writeToFile("bookChanges.txt", message);
    }
}
