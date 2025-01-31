package com.example.springboot_shopingapp.repository;

import com.example.springboot_shopingapp.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Feature findByName(String name);

    void deleteById(Long id);
}
