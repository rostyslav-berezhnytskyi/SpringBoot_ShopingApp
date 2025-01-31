package com.example.springboot_shopingapp.dto.mapper;

import com.example.springboot_shopingapp.dto.feature.FeatureResponseDto;
import com.example.springboot_shopingapp.dto.manufacture.ManufactureResponseDto;
import com.example.springboot_shopingapp.dto.phone.PhoneRequestDto;
import com.example.springboot_shopingapp.dto.phone.PhoneResponseDto;
import com.example.springboot_shopingapp.model.Feature;
import com.example.springboot_shopingapp.model.Manufacture;
import com.example.springboot_shopingapp.model.Phone;
import com.example.springboot_shopingapp.service.FeatureService;
import com.example.springboot_shopingapp.service.ManufactureService;
import com.example.springboot_shopingapp.service.PhoneService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {
    private final ManufactureService manufactureService;
    private final FeatureService featureService;
    private final FeatureMapper featureMapper;
    private final ManufactureMapper manufactureMapper;
    private final PhoneService phoneService;

    @Autowired
    public PhoneMapper(ManufactureService manufactureService, FeatureService featureService, FeatureMapper featureMapper, ManufactureMapper manufactureMapper, PhoneService phoneService) {
        this.manufactureService = manufactureService;
        this.featureService = featureService;
        this.featureMapper = featureMapper;
        this.manufactureMapper = manufactureMapper;
        this.phoneService = phoneService;
    }

    public Phone toModel(PhoneRequestDto requestDto) {
        Phone phone = new Phone();
        phone.setModel(requestDto.model());
        phone.setManufacture(manufactureService.findByName(requestDto.manufacture().name()));
        phone.setFeatures(requestDto.features().stream().map(featureMapper::toModel).map(manu -> featureService.findByName(manu.getName())).collect(Collectors.toSet()));
        phone.setColor(requestDto.color());
        phone.setCountryManufactured(requestDto.countryManufactured());
        return phone;
    }

    public PhoneResponseDto toResponseDto (Phone phone) {
        return new PhoneResponseDto(
                phone.getModel(),
                manufactureToResponseDto(phone.getManufacture()),
                featureToResponseDto(phone.getFeatures()),
                phone.getColor(),
                phone.getCountryManufactured());
    }

    private ManufactureResponseDto manufactureToResponseDto(Manufacture manufacture) {
        return manufactureMapper.toResponseDto(manufactureService.findById(manufacture.getId()));
    }

    private Set<FeatureResponseDto> featureToResponseDto(Set<Feature> features) {
        return features.stream().map(featureMapper::toResponse).collect(Collectors.toSet());
    }
}
