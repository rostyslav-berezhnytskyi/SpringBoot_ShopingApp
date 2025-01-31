package com.example.springboot_shopingapp.controller;

import com.example.springboot_shopingapp.dto.feature.FeatureRequestDto;
import com.example.springboot_shopingapp.dto.feature.FeatureResponseDto;
import com.example.springboot_shopingapp.dto.mapper.FeatureMapper;
import com.example.springboot_shopingapp.model.Feature;
import com.example.springboot_shopingapp.service.FeatureService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/features")
public class FeatureController {
    private final FeatureService featureService;
    private final FeatureMapper featureMapper;

    public FeatureController(FeatureService featureService, FeatureMapper featureMapper) {
        this.featureService = featureService;
        this.featureMapper = featureMapper;
    }

    @GetMapping("/{id}")
    public FeatureResponseDto findById(@PathVariable Long id) {
        return featureMapper.toResponse(featureService.findById(id));
    }

    @GetMapping("/findAll")
    public List<FeatureResponseDto> findAll() {
        return featureService.findAll().stream().map(featureMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    public FeatureResponseDto save(@RequestBody FeatureRequestDto featureRequestDto) {
        Feature model = featureMapper.toModel(featureRequestDto);
        Feature saveModel = featureService.save(model);
        return featureMapper.toResponse(saveModel);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        featureService.deleteById(id);
        return "Deleted Feature successfully";
    }

}
