package com.example.springboot_shopingapp.dto.product;

import java.math.BigDecimal;

public record ProductResponseDto (Long id, String title, BigDecimal price) {
}
