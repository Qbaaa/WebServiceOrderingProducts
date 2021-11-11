package com.qbaaa.restOrderingProducts.service;

import com.qbaaa.restOrderingProducts.DTO.ProductDTO;
import com.qbaaa.restOrderingProducts.model.ProductModel;
import com.qbaaa.restOrderingProducts.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public boolean add(ProductDTO productDTO) {

        if ( existsByName( productDTO.getName() ).isPresent() ) {

            return false;
        }
        else {

            ProductModel productModel = new ProductModel(productDTO.getName(), productDTO.getPrice());
            productRepository.save(productModel);
            return true;
        }
    }

    public List<ProductModel> getAll() {

        return productRepository.findAll();
    }

    private Optional<ProductModel> existsByName(String name) {

        return productRepository.findByName(name);
    }

    public boolean updateProduct(ProductDTO oldProduct, ProductDTO newProduct) {

        Optional<ProductModel> updateProduct = existsByName( oldProduct.getName() );

        if (updateProduct.isPresent()) {

            if (existsByName(newProduct.getName()).isPresent()) {

                return false;
            }
            else {

                updateProduct.get().setName(newProduct.getName());
                updateProduct.get().setPrice(newProduct.getPrice());

                productRepository.save(updateProduct.get());
                return true;
            }
        }
        else {

            return false;
        }
    }

    public boolean updateNameProduct(String oldNameProduct, String newNameProduct) {

        Optional<ProductModel> updateProduct = existsByName( oldNameProduct );

        if (updateProduct.isPresent()) {

            if (existsByName(newNameProduct).isPresent()) {

                return false;
            }
            else {

                updateProduct.get().setName(newNameProduct);

                productRepository.save(updateProduct.get());
                return true;
            }
        }
        else {

            return false;
        }
    }

    public boolean updatePriceProduct(String oldNameProduct, double newPriceProduct) {

        Optional<ProductModel> updateProduct = existsByName( oldNameProduct );

        if (updateProduct.isPresent()) {

            updateProduct.get().setPrice(newPriceProduct);
            productRepository.save(updateProduct.get());
            return true;
        }
        else {

            return false;
        }
    }
}
