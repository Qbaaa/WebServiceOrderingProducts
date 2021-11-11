package com.qbaaa.restOrderingProducts.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class ProductModel {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @PositiveOrZero
    @Column(nullable = false)
    private double price;

    @ManyToMany(mappedBy = "products")
    private Set<OrderModel> orders;

    public ProductModel() {
        orders = new LinkedHashSet<>();
    }

    public ProductModel(String name, double price) {
        this.name = name;
        this.price = price;
        orders = new LinkedHashSet<>();
    }

    public Set<OrderModel> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
