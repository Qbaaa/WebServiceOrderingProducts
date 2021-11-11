package com.qbaaa.restOrderingProducts.service;

import com.qbaaa.restOrderingProducts.model.OrderModel;
import com.qbaaa.restOrderingProducts.model.ProductModel;
import com.qbaaa.restOrderingProducts.repository.OrderRepository;
import com.qbaaa.restOrderingProducts.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    final ProductRepository productRepository;
    final OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository) {

        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public double make(List<Long> productsId) {

        OrderModel order = new OrderModel();

        productsId.forEach(
                ele -> {

                    Optional<ProductModel> product = productRepository.findById( ele );

                    if ( product.isPresent() ) {

                        order.addProduct( product.get() );
                    }
                }
        );

        order.setSumPrice( order.getProducts().stream().mapToDouble( ele -> ele.getPrice() ).sum() );
        order.setStartData( LocalDate.now() );

        orderRepository.save( order );

        return order.getSumPrice();
    }

    public List<OrderModel> getPeriodDate(LocalDate start, LocalDate end) {

        List<OrderModel> orders = orderRepository.findAllByStartDataGreaterThanEqualAndStartDataLessThanEqual( start, end );

        for ( OrderModel order : orders ) {

            order.setSumPrice( order.getProducts().stream().mapToDouble( ele -> ele.getPrice() ).sum() );
            orderRepository.save(order);
        }

        return orders;
    }

    public boolean calculate(Long id) {

        Optional<OrderModel> order = orderRepository.findById(id);

        if ( order.isPresent() ) {

            order.get().setSumPrice( order.get().getProducts().stream().mapToDouble( ele -> ele.getPrice() ).sum() );
            orderRepository.save(order.get());
            return true;
        }

        return false;
    }
}
