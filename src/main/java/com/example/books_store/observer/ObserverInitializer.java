package com.example.books_store.observer;

import com.example.books_store.model.FileLoggerObserver;
import com.example.books_store.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObserverInitializer implements CommandLineRunner {

    private final BookService service;
    private final FileLoggerObserver loggerObserver;

    @Override
    public void run(String... args) throws Exception {
        service.regObserver(loggerObserver);
    }
}
