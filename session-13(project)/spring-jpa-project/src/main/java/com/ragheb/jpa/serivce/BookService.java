package com.ragheb.jpa.serivce;

import com.ragheb.jpa.entity.Book;
import com.ragheb.jpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        if (book.getId() != null) {
            throw new RuntimeException("Cannot save a book with an existing ID");
        }
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        Book existingBook = findById(book.getId());
        existingBook.setName(book.getName());
        existingBook.setPrice(book.getPrice());
        existingBook.setAuthor(book.getAuthor());
        return bookRepository.save(existingBook);
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void deleteBooksByAuthorId(Integer id) {
        bookRepository.deleteAuthorById(id);
    }
}
