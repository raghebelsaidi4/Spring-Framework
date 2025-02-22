package com.ragheb.jpa;

import com.github.javafaker.Faker;
import com.ragheb.jpa.entity.Author;
import com.ragheb.jpa.entity.Book;
import com.ragheb.jpa.serivce.AuthorService;
import com.ragheb.jpa.serivce.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringJpaProjectApplication {

    private final AuthorService authorService;
    private final BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaProjectApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(AuthorService authorService, BookService bookService) {
//        return args -> {
//            Faker faker = new Faker();
//
//            for (int i = 0; i < 50; i++) {
//                // Create and save an Author
//                Author author = new Author();
//                author.setName(faker.name().fullName());
//                Author savedAuthor = authorService.save(author);
//
//                // Create and save a Book associated with the Author
//                Book book = new Book();
//                book.setName(faker.book().title());
//                book.setPrice(faker.number().randomDouble(2, 10, 200));
//                book.setAuthor(savedAuthor);
//
//                bookService.save(book);
//            }
//        };
//    }
}
