package com.example.springboot_shopingapp.service;

import com.example.springboot_shopingapp.model.Manufacture;
import com.example.springboot_shopingapp.repository.ManufactureRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufactureServiceImpl implements ManufactureService{
    private final ManufactureRepository manufactureRepository;

    @Autowired
    public ManufactureServiceImpl(ManufactureRepository manufactureRepository) {
        this.manufactureRepository = manufactureRepository;
    }

    @Override
    public Manufacture save(Manufacture manufacture) {
        return manufactureRepository.save(manufacture);
    }

    @Override
    public Manufacture findByName(String name) {
        return manufactureRepository.findByName(name);
    }

    @Override
    public Manufacture findById(Long id) {
        return manufactureRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Manufacture> findAll() {
        return manufactureRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        manufactureRepository.deleteById(id);
    }

    @Override
    public Manufacture update(Manufacture manufacture, Long id) {
        return null;
    }
}
