package com.ragheb.jpa.controller;

import com.ragheb.jpa.entity.Book;
import com.ragheb.jpa.serivce.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        try {
            Book savedBook = bookService.save(book);
            return ResponseEntity.ok(savedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {
        try {
            Book updatedBook = bookService.update(book);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        bookService.deleteById(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> deleteBookByAuthorId(@PathVariable Integer id) {
        bookService.deleteBooksByAuthorId(id);
        return ResponseEntity.ok("Author deleted successfully");
    }
}
