package com.example.springboot_shopingapp.service;

import com.example.springboot_shopingapp.model.Feature;
import com.example.springboot_shopingapp.repository.FeatureRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureServiceImpl implements FeatureService {
    private final FeatureRepository featureRepository;

    @Autowired
    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public Feature save(Feature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public Feature findByName(String name) {
        return featureRepository.findByName(name);
    }

    @Override
    public Feature findById(Long id) {
        return featureRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Feature> findAll() {
        return featureRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        featureRepository.deleteById(id);
    }

    @Override
    public Feature update(Feature feature, Long id) {
        return null;
    }
}
