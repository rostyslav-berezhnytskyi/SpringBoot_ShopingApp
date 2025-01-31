package com.example.springboot_shopingapp.service;

import com.example.springboot_shopingapp.model.Phone;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;

public interface PhoneService {
    List<Phone> findAll(Map<String, String> params);

    List<Phone> findAll(PageRequest pageRequest);

    Phone save(Phone phone);

    Phone findByModel(String model);

    Phone findById(Long id);

    List<Phone> findAll();

    void deleteById(Long id);

    Phone update(Phone phone, Long id);
}
