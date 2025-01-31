package com.example.springboot_shopingapp.dto.product;

import java.math.BigDecimal;

public record ProductRequestDto(String title, BigDecimal price) {
}
