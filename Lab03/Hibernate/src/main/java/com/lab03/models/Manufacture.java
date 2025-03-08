package com.lab03.models;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture {
    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    @Column(name = "Name", length = 128, nullable = false)
    private String name;

    @Column(name = "Location", length = 128)
    private String location;

    @Column(name = "Employee", nullable = false)
    private int employee;

    @OneToMany(mappedBy = "manufacture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones;

    // Constructors
    public Manufacture() {}

    public Manufacture(String id, String name, String location, int employee) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getEmployee() { return employee; }
    public void setEmployee(int employee) { this.employee = employee; }

    public List<Phone> getPhones() { return phones; }
    public void setPhones(List<Phone> phones) { this.phones = phones; }
}
