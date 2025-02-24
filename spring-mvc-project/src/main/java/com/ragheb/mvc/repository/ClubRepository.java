package com.ragheb.mvc.repository;

import com.ragheb.mvc.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByTitle(String url);

    @Query("SELECT c FROM Club c WHERE c.title LIKE concat('%',:query,'%') ")
    List<Club> searchClubs(String query);
}
