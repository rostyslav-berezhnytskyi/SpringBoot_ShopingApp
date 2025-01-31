package com.example.springboot_shopingapp.repository;

import com.example.springboot_shopingapp.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
    Manufacture findByName(String name);

    void deleteById(Long id);
}
