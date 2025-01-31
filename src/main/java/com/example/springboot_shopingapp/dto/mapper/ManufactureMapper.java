package com.example.springboot_shopingapp.dto.mapper;

import com.example.springboot_shopingapp.dto.manufacture.ManufacturRequestDto;
import com.example.springboot_shopingapp.dto.manufacture.ManufactureResponseDto;
import com.example.springboot_shopingapp.model.Manufacture;
import com.example.springboot_shopingapp.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManufactureMapper {
    private final ManufactureService manufactureService;

    @Autowired
    public ManufactureMapper(ManufactureService manufactureService) {
        this.manufactureService = manufactureService;
    }

    public ManufactureResponseDto toResponseDto(Manufacture manufacture) {
        return new ManufactureResponseDto(manufacture.getName());
    }

    public Manufacture toModel(ManufacturRequestDto requestDto) {
        Manufacture manufacture = new Manufacture();
        manufacture.setName(requestDto.name());
        return manufacture;
    }
}
