package com.ragheb.jpa.repository;

import com.ragheb.jpa.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Override
    @EntityGraph(attributePaths = {"author"})
    Optional<Book> findById(Integer id);

    @Override
    @EntityGraph(attributePaths = {"author"})
    List<Book> findAll();

    @Query(value = "select b from Book b where b.name = :name")
    List<Book> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "delete from Book where author.id = :id")
    void deleteAuthorById(Integer id);
}
