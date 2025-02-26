package com.ragheb.restful.repository;

import com.ragheb.restful.model.Image;
import com.ragheb.restful.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    List<Image> findByProductId(Long id);
}
