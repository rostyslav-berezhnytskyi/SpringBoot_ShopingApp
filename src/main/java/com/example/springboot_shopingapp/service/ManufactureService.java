package com.example.springboot_shopingapp.service;

import com.example.springboot_shopingapp.model.Manufacture;
import java.util.List;

public interface ManufactureService {
    Manufacture save(Manufacture manufacture);

    Manufacture findByName(String name);

    Manufacture findById(Long id);

    List<Manufacture> findAll();

    void deleteById(Long id);

    Manufacture update(Manufacture manufacture, Long id);
}
