package com.ragheb.jpa.repository;

import com.ragheb.jpa.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer>, JpaSpecificationExecutor<Author> {
}
