package com.example.springboot_shopingapp.repository.specification.phone;

import com.example.springboot_shopingapp.model.Phone;
import com.example.springboot_shopingapp.repository.specification.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PhoneModelInSpecification implements SpecificationProvider<Phone> {
    private static final String FILTER_KEY = "modelIn";
    private static final String FIELD_NAME = "model";

    @Override
    public Specification<Phone> getSpecification(String[] models) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FIELD_NAME));
            for (String value : models) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
