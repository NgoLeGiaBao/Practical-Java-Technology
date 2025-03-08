package com.lab03;

import com.lab03.daos.*;
import com.lab03.models.*;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize DAO instances
        ManufactureDAO manufactureDAO = new ManufactureDAO();
        PhoneDAO phoneDAO = new PhoneDAO();

        // Add a new manufacturer
        Manufacture samsung = new Manufacture("M001", "Samsung", "South Korea", 300000);
        manufactureDAO.saveManufacture(samsung);
        System.out.println("Added Manufacture: " + samsung.getName());

        // Add a phone under the manufacturer
        Phone galaxyS24 = new Phone("P001", "Galaxy S24", 25000000, "Black", "Vietnam", 100, samsung);
        phoneDAO.savePhone(galaxyS24);
        System.out.println("Added Phone: " + galaxyS24.getName());

        // Retrieve all phones
        List<Phone> phoneList = phoneDAO.getAllPhones();
        System.out.println("\nPhone List:");
        for (Phone phone : phoneList) {
            System.out.println("- " + phone.getName() + " | " + phone.getPrice() + " VND");
        }

        // Update phone information
        galaxyS24.setPrice(23000000);
        phoneDAO.updatePhone(galaxyS24);
        System.out.println("Updated price for " + galaxyS24.getName());

        // Delete a phone
        phoneDAO.deletePhone("P001");
        System.out.println("Deleted phone with ID: P001");
    }
}
