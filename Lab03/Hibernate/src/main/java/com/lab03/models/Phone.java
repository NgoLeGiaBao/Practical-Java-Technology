package com.lab03.models;
import jakarta.persistence.*;

@Entity
@Table(name = "MobilePhone")
public class Phone {

    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    @Column(name = "Name", length = 128, nullable = false)
    private String name;

    @Column(name = "Price", nullable = false)
    private int price;

    @Column(name = "Color", length = 50, nullable = false)
    private String color;

    @Column(name = "Country", length = 50)
    private String country;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ManufactureID", nullable = false)
    private Manufacture manufacture;

    // Constructors
    public Phone() {}

    public Phone(String id, String name, int price, String color, String country, int quantity, Manufacture manufacture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
        this.manufacture = manufacture;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Manufacture getManufacture() { return manufacture; }
    public void setManufacture(Manufacture manufacture) { this.manufacture = manufacture; }
}