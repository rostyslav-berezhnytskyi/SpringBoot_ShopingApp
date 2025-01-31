package com.example.springboot_shopingapp.repository.specification;

import com.example.springboot_shopingapp.model.Phone;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PhoneSpecificationManager implements SpecificationManager<Phone> {
    private final Map<String, SpecificationProvider<Phone>> providersMap;

    @Autowired
    public PhoneSpecificationManager(List<SpecificationProvider<Phone>> phoneSpecifications) {
            this.providersMap = phoneSpecifications.stream().collect(Collectors.toMap(SpecificationProvider::getFilterKey, Function.identity()));
    }

    @Override
    public Specification<Phone> get(String filterKey, String[] params) {
        if(!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is not supported for data filtering");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
