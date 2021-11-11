package com.qbaaa.restOrderingProducts.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class ProductDTO {

    @NotNull
    @NotBlank
    private final String name;

    @PositiveOrZero
    private final double price;

    public ProductDTO(String name, double price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

    @Override
    public String toString() {
        return "\nProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
