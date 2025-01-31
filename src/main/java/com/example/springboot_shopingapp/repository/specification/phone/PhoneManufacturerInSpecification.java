package com.example.springboot_shopingapp.repository.specification.phone;

import com.example.springboot_shopingapp.model.Manufacture;
import com.example.springboot_shopingapp.model.Phone;
import com.example.springboot_shopingapp.repository.specification.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PhoneManufacturerInSpecification implements SpecificationProvider<Phone> {
    private static final String FILTER_KEY = "manufacturerIn";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Phone> getSpecification(String[] manufacturers) {
        return (root, query, cb) -> {
            Join<Phone, Manufacture> join = root.join("manufacture", JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(join.get(FIELD_NAME));
            for (String value : manufacturers) {
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
