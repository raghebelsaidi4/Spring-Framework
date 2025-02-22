package com.ragheb.jpa.serivce;

import com.ragheb.jpa.entity.Author;
import com.ragheb.jpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author findById(Integer id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author save(Author author) {
        if (author.getId() != null) {
            throw new RuntimeException();
        }
        return authorRepository.save(author);
    }

    public Author update(Author author) {
        Author authorById = findById(author.getId());
        authorById.setName(author.getName());
        return authorRepository.save(authorById);
    }

    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }
}
