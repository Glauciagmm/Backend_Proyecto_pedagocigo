package com.uniquecare.pedagogico_backend.repositories;

import com.uniquecare.pedagogico_backend.models.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilitRepository extends JpaRepository<Facility, Long> {
    List<Facility> findAllByCategoryId(Long categoryId);
    List<Facility> findAllByCategoryName(String categoryName);
}
