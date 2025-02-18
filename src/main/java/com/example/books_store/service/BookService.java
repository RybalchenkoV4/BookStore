package com.example.books_store.service;

import com.example.books_store.model.Book;
import com.example.books_store.observer.Observer;
import com.example.books_store.observer.Subject;
import com.example.books_store.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService implements Subject {

    private final BookRepository repository;
    private final List<Observer> observers = new ArrayList<>();

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public Book save(Book book) {
        Book savedBook = repository.save(book);
        notifyObservers(savedBook, "appeared in stock");
        return savedBook;
    }

    public void deleteById(Long id) {
        Optional<Book> book = repository.findById(id);
        if(book.isPresent()) {
            repository.deleteById(id);
            notifyObservers(book.get(), "ended in the store");
        }

    }

    @Override
    public void regObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Book book, String action) {
        for(Observer obs: observers) {
            obs.update(book, action);
        }
    }
}
