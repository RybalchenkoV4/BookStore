package com.example.books_store.controller;

import com.example.books_store.model.Book;
import com.example.books_store.service.BookService;
import com.example.books_store.service.FileGateway;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    private final FileGateway fileGateway;

    private void writeCallToFile() {

        String message = new Throwable()
                .getStackTrace()[1]
                .getMethodName() + " - " +
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"));

        fileGateway.writeToFile("usedMethods.txt", message);
    }

    @GetMapping
    public List<Book> findAll() {
        writeCallToFile();
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Optional<Book> book = service.findById(id);
        writeCallToFile();
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        writeCallToFile();
        return service.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        book.setId(id);
        writeCallToFile();
        return service.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        writeCallToFile();
        service.deleteById(id);
    }
}
