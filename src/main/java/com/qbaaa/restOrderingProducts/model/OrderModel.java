package com.qbaaa.restOrderingProducts.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERING")
public class OrderModel {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ORDER_PRODUCT",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns =  {@JoinColumn(name = "product_id") })
    private List<ProductModel> products;
    private double sumPrice;
    private LocalDate startData;

    public OrderModel() {
        products = new ArrayList<>();
    }

    public void addProduct(ProductModel product) {

        products.add(product);
        product.getOrders().add(this);
    }

    public void removeProduct(ProductModel product) {

        products.remove(product);
        product.getOrders().remove(this);
    }

    public Long getId() { return id; }

    public List<ProductModel> getProducts() {
        return products;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public LocalDate getStartData() {
        return startData;
    }

    public void setStartData(LocalDate startData) {
        this.startData = startData;
    }
}
