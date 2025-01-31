package com.example.springboot_shopingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @ManyToOne
    private Manufacture manufacture;
    @ManyToMany
    @JoinTable(name = "phone_feature",
            joinColumns = @JoinColumn(name = "phone_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private Set<Feature> features;
    private String color;
    private String countryManufactured;

    public Phone() {}

    public Phone(String model, Manufacture manufacture, Set<Feature> features, String color, String countryManufactured) {
        this.model = model;
        this.manufacture = manufacture;
        this.features = features;
        this.color = color;
        this.countryManufactured = countryManufactured;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountryManufactured() {
        return countryManufactured;
    }

    public void setCountryManufactured(String countryManufactured) {
        this.countryManufactured = countryManufactured;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacture=" + manufacture +
                ", features=" + features +
                ", color='" + color + '\'' +
                ", countryManufactured='" + countryManufactured + '\'' +
                '}';
    }
}
