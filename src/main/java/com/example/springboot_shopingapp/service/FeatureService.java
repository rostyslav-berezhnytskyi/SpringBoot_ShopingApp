package com.example.springboot_shopingapp.service;

import com.example.springboot_shopingapp.model.Feature;
import java.util.List;

public interface FeatureService {
    Feature save(Feature feature);

    Feature findByName(String name);

    Feature findById(Long id);

    List<Feature> findAll();

    void deleteById(Long id);

    Feature update(Feature feature, Long id);
}
