package com.example.springboot_shopingapp.controller;

import com.example.springboot_shopingapp.dto.manufacture.ManufacturRequestDto;
import com.example.springboot_shopingapp.dto.manufacture.ManufactureResponseDto;
import com.example.springboot_shopingapp.dto.mapper.ManufactureMapper;
import com.example.springboot_shopingapp.model.Manufacture;
import com.example.springboot_shopingapp.service.ManufactureService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufactureService manufactureService;
    private final ManufactureMapper manufactureMapper;

    @Autowired
    public ManufacturerController(ManufactureService manufactureService, ManufactureMapper manufactureMapper) {
        this.manufactureService = manufactureService;
        this.manufactureMapper = manufactureMapper;
    }

    @GetMapping("/{id}")
    public ManufactureResponseDto findById(@PathVariable Long id) {
        return manufactureMapper.toResponseDto(manufactureService.findById(id));
    }

    @GetMapping("/findAll")
    public List<ManufactureResponseDto> findAll() {
        return manufactureService.findAll().stream().map(manufactureMapper::toResponseDto).collect(Collectors.toList());
    }

    @PostMapping
    public ManufactureResponseDto save(@RequestBody ManufacturRequestDto requestDto) {
        Manufacture model = manufactureMapper.toModel(requestDto);
        Manufacture saveModel = manufactureService.save(model);
        return manufactureMapper.toResponseDto(saveModel);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        manufactureService.deleteById(id);
        return "Manufacturer deleted successfully";
    }
}
