package com.example.springboot_shopingapp.controller;

import com.example.springboot_shopingapp.dto.mapper.PhoneMapper;
import com.example.springboot_shopingapp.dto.phone.PhoneRequestDto;
import com.example.springboot_shopingapp.dto.phone.PhoneResponseDto;
import com.example.springboot_shopingapp.model.Feature;
import com.example.springboot_shopingapp.model.Manufacture;
import com.example.springboot_shopingapp.model.Phone;
import com.example.springboot_shopingapp.service.FeatureService;
import com.example.springboot_shopingapp.service.ManufactureService;
import com.example.springboot_shopingapp.service.PhoneService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phones")
@Tag(name = "Phone Controller New Name")
public class PhoneController {
    private final FeatureService featureService;
    private final PhoneService phoneService;
    private final ManufactureService manufactureService;
    private final PhoneMapper phoneMapper;

    @Autowired
    public PhoneController(FeatureService featureService, PhoneService phoneService, ManufactureService manufactureService, PhoneMapper phoneMapper) {
        this.featureService = featureService;
        this.phoneService = phoneService;
        this.manufactureService = manufactureService;
        this.phoneMapper = phoneMapper;
    }

    @GetMapping("/{id}")
    public PhoneResponseDto getPhone(@PathVariable Long id) {
        return phoneMapper.toResponseDto(phoneService.findById(id));
    }

    @PostMapping
    @Operation(description = "Take PhoneRequestDto and create Phone entity",
                summary = "This is a summary for phone post endpoint",
                responses = {
                        @ApiResponse(
                                description = "Success",
                            responseCode = "200")
                })
    public PhoneResponseDto create(@RequestBody PhoneRequestDto phoneRequestDto) {
        return phoneMapper.toResponseDto(phoneService.save(phoneMapper.toModel(phoneRequestDto)));
    }

    @GetMapping
    public List<PhoneResponseDto> findAll(@RequestParam Map<String, String> params) {
        return phoneService.findAll(params).stream()
                .map(phoneMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findAll")
    @Operation(description = "Get product List")
    public List<PhoneResponseDto> getAllPhones(@RequestParam (defaultValue = "20") Integer count,
                                               @RequestParam (defaultValue = "1") Integer page,
                                                @RequestParam (defaultValue = "id") String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if(sortBy.contains(":")) {
            String[] sortingFields = sortBy.split(";");
            for(String field : sortingFields) {
                Sort.Order order;
                if(field.contains(":")) {
                    String[] fieldAndDirections = field.split(":");
                    order = new Sort.Order(Sort.Direction.valueOf(fieldAndDirections[1]), fieldAndDirections[0]);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, field);
                }
                orders.add(order);
            }
        } else {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, sortBy);
            orders.add(order);
        }
        Sort sort = Sort.by(orders);
        PageRequest pageRequest = PageRequest.of(page, count, sort);
        return phoneService.findAll(pageRequest).stream().map(phoneMapper::toResponseDto).collect(Collectors.toList());
    }

    @GetMapping("/inject")
    @Hidden
    public String inject(@RequestParam(defaultValue = "100") Integer count) {
        if(featureService.findByName("NFS") == null) {
            saveFeatures();
        }
        if(manufactureService.findByName("Apple") == null) {
            saveManufactures();
        }
        String[] features = {"NFC", "autofocus", "flash", "stabilization"};
        String[] manufactures = {"Apple", "Xiaomi", "Samsung", "Nokia", "Google"};
        String[] colors = {"red", "green", "blue", "yellow", "purple"};
        String[] countries = {"USA", "Europe", "China", "Japan", "Korea"};
        Set<Feature> featureSet = new HashSet<>();

        for (int i = 0; i < count; i++) {
            Phone phone = new Phone();
            for (int j = 0; j < new Random().nextInt(features.length); j++) {
                Feature feature = featureService.findByName(features[j]);
                featureSet.add(feature);
            }
            phone.setManufacture(manufactureService.findByName(manufactures[new Random().nextInt(manufactures.length)]));
            phone.setFeatures(featureSet);
            phone.setModel(UUID.randomUUID().toString());
            phone.setColor(colors[new Random().nextInt(colors.length)]);
            phone.setCountryManufactured(countries[new Random().nextInt(countries.length)]);
            phoneService.save(phone);
        }
        return "Injected " + count + " phones successfully";
    }

    private void saveFeatures() {
        String[] features = {"NFC", "autofocus", "flash", "stabilization"};
        for (String name : features) {
            featureService.save(new Feature(name));
        }
    }

    private void saveManufactures() {
        String[] manufactures = {"Apple", "Xiaomi", "Samsung", "Nokia", "Google"};
        for (String name : manufactures) {
            manufactureService.save(new Manufacture(name));
        }
    }
}
