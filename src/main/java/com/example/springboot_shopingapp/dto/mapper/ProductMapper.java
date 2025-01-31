package com.example.springboot_shopingapp.dto.mapper;

import com.example.springboot_shopingapp.dto.product.ProductRequestDto;
import com.example.springboot_shopingapp.dto.product.ProductResponseDto;
import com.example.springboot_shopingapp.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getTitle(), product.getPrice());
    }

    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setTitle(requestDto.title());
        product.setPrice(requestDto.price());
        return product;
    }
}
