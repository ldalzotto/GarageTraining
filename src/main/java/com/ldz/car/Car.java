package com.ldz.car;

import com.ldz.garage.Garage;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private String model;
    private String brand;
    private String imat;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;


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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImat() {
        return imat;
    }

    public void setImat(String imat) {
        this.imat = imat;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
