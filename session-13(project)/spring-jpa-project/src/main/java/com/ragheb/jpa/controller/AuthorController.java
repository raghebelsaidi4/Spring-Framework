package com.ragheb.jpa.controller;

import com.ragheb.jpa.entity.Author;
import com.ragheb.jpa.serivce.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author) {
        try {
            Author savedAuthor = authorService.save(author);
            return ResponseEntity.ok(savedAuthor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Author> updateAuthor(@Valid @RequestBody Author author) {
        try {
            Author updatedAuthor = authorService.update(author);
            return ResponseEntity.ok(updatedAuthor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteById(id);
        return ResponseEntity.ok("Author deleted successfully");
    }
}
