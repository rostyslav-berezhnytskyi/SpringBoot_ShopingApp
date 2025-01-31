package com.example.springboot_shopingapp.dto.phone;

import com.example.springboot_shopingapp.dto.feature.FeatureRequestDto;
import com.example.springboot_shopingapp.dto.manufacture.ManufacturRequestDto;
import java.util.Set;

public record PhoneRequestDto(String model,
                              ManufacturRequestDto manufacture,
                              Set<FeatureRequestDto> features,
                              String color,
                              String countryManufactured) {
}
