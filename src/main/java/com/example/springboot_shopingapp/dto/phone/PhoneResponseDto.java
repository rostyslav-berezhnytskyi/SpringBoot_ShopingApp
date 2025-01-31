package com.example.springboot_shopingapp.dto.phone;

import com.example.springboot_shopingapp.dto.feature.FeatureResponseDto;
import com.example.springboot_shopingapp.dto.manufacture.ManufactureResponseDto;
import java.util.Set;

public record PhoneResponseDto(String model,
                               ManufactureResponseDto manufacture,
                               Set<FeatureResponseDto> features,
                               String color,
                               String countryManufactured) {}
