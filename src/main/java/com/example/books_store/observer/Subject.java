package com.example.books_store.observer;

import com.example.books_store.model.Book;

public interface Subject {
    void regObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Book book, String action);
}

