package com.qbaaa.restOrderingProducts.controller;

import com.qbaaa.restOrderingProducts.DTO.ProductDTO;
import com.qbaaa.restOrderingProducts.model.ProductModel;
import com.qbaaa.restOrderingProducts.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@Valid @RequestBody ProductDTO product) {

        if ( productService.add(product) ) {

            return new ResponseEntity<>("Product: " + product + " added to the database", HttpStatus.CREATED);
        }
        else {

            return new ResponseEntity<>("Product with the given name: " + product + " is already in the database ", HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<String> getAll() {

        List<ProductModel> products = productService.getAll();

        if ( !products.isEmpty() ) {

            return new ResponseEntity<>( products.toString(), HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>("In database does not have products.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody ProductDTO oldProduct, @Valid @RequestBody ProductDTO newProduct) {

        if (productService.updateProduct(oldProduct, newProduct)) {

            return new ResponseEntity<>("Product: " + oldProduct +
                                        "updated on Product: " + newProduct, HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>("Update product failed.", HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping("/update/name")
    public ResponseEntity<String> updateName(@NotNull @NotBlank @RequestParam(value = "oldNameProduct") String oldNameProduct,
                              @NotNull @NotBlank @RequestParam(value = "newNameProduct") String newNameProduct) {

        if ( productService.updateNameProduct(oldNameProduct, newNameProduct) ) {

            return new ResponseEntity<>("Product named: " + oldNameProduct +
                    "updated on Product named: " + newNameProduct, HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>("Update product named failed.", HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping("/update/price")
    public ResponseEntity<String> updateName(@NotNull @NotBlank @RequestParam(value = "oldNameProduct") String oldNameProduct,
                                             @NotNull @NotBlank @RequestParam(value = "newPriceProduct") double newPriceProduct) {

        if ( productService.updatePriceProduct(oldNameProduct, newPriceProduct) ) {

            return new ResponseEntity<>("Product named: " + oldNameProduct +
                    " updated on Product priced: " + newPriceProduct, HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>("Update product priced failed.", HttpStatus.NOT_MODIFIED);
        }
    }

}
