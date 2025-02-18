package com.example.books_store.observer;

import com.example.books_store.model.Book;

public interface Observer {
    void update(Book book, String action);
}
