package com.example.springboot_shopingapp.controller;

import com.example.springboot_shopingapp.dto.mapper.ProductMapper;
import com.example.springboot_shopingapp.dto.product.ProductRequestDto;
import com.example.springboot_shopingapp.dto.product.ProductResponseDto;
import com.example.springboot_shopingapp.model.Product;
import com.example.springboot_shopingapp.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product savedProduct = productService.save(productMapper.toModel(requestDto));
        return productMapper.toResponseDto(savedProduct);
    }

    @GetMapping("/getAll")
    public List<ProductResponseDto> getAll() {
        return productService.findAll()
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDto getStringById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return productMapper.toResponseDto(product);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPrice(@RequestParam BigDecimal from, @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String saveProducts() {
        Product iphone8 = new Product();
        iphone8.setTitle("iPhone 8");
        iphone8.setPrice(BigDecimal.valueOf(699));
        productService.save(iphone8);

        Product iphoneX = new Product();
        iphoneX.setTitle("iPhone X");
        iphoneX.setPrice(BigDecimal.valueOf(1099));
        productService.save(iphoneX);

        Product iphone11 = new Product();
        iphone11.setTitle("iPhone 11");
        iphone11.setPrice(BigDecimal.valueOf(1199));
        productService.save(iphone11);

        Product iphone12 = new Product();
        iphone12.setTitle("iPhone 12");
        iphone12.setPrice(BigDecimal.valueOf(1399));
        productService.save(iphone12);
        return "Products successfully injected!";
    }
}
