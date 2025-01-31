package com.example.springboot_shopingapp.repository.specification.phone;

import com.example.springboot_shopingapp.model.Feature;
import com.example.springboot_shopingapp.model.Phone;
import com.example.springboot_shopingapp.repository.specification.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.SetJoin;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PhoneFeatureInSpecification implements SpecificationProvider<Phone> {
    private static final String FILTER_KEY = "featureIn";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Phone> getSpecification(String[] features) {
        return (root, query, cb) -> {
            SetJoin<Phone, Feature> phones = root.joinSet("features", JoinType.LEFT);
            CriteriaBuilder.In<String> predicate = cb.in(phones.get(FIELD_NAME));
            for (String value : features) {
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
