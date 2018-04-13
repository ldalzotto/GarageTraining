package com.ldz.garage;

import com.ldz.car.Car;
import com.ldz.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Garage {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer maxcapacity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "garage")
    private List<Car> cars = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxcapacity() {
        return maxcapacity;
    }

    public void setMaxcapacity(Integer maxcapacity) {
        this.maxcapacity = maxcapacity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
