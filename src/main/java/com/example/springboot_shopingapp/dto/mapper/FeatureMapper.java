package com.example.springboot_shopingapp.dto.mapper;

import com.example.springboot_shopingapp.dto.feature.FeatureRequestDto;
import com.example.springboot_shopingapp.dto.feature.FeatureResponseDto;
import com.example.springboot_shopingapp.model.Feature;
import com.example.springboot_shopingapp.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatureMapper {
    private final FeatureService featureService;

    @Autowired
    public FeatureMapper(FeatureService featureService) {
        this.featureService = featureService;
    }

    public Feature toModel(FeatureRequestDto featureRequestDto) {
        Feature feature = new Feature();
        feature.setName(featureRequestDto.name());
        return feature;
    }

    public FeatureResponseDto toResponse(Feature feature) {
        return new FeatureResponseDto(feature.getName());
    }
}
