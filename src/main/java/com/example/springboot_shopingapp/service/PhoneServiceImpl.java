package com.example.springboot_shopingapp.service;

import com.example.springboot_shopingapp.model.Phone;
import com.example.springboot_shopingapp.repository.PhoneRepository;
import com.example.springboot_shopingapp.repository.specification.SpecificationManager;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final SpecificationManager<Phone> phoneSpecificationManager;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, SpecificationManager<Phone> phoneSpecificationManager) {
        this.phoneRepository = phoneRepository;
        this.phoneSpecificationManager = phoneSpecificationManager;
    }

    @Override
    public List<Phone> findAll(Map<String, String> params) {
        Specification<Phone> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Phone> sp = phoneSpecificationManager.get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }
        return phoneRepository.findAll(specification);
    }

    @Override
    public List<Phone> findAll(PageRequest pageRequest) {
        List<Phone> list = phoneRepository.findAll(pageRequest).toList();
        Page<Phone> all = phoneRepository.findAll(pageRequest);
        return list;
    }

    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Phone findByModel(String model) {
        return phoneRepository.findByModel(model);
    }

    @Override
    public Phone findById(Long id) {
        return phoneRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        phoneRepository.deleteById(id);
    }

    @Override
    public Phone update(Phone phone, Long id) {
        return null;
    }
}
