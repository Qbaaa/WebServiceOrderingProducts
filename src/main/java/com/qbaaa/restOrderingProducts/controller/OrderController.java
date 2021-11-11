package com.qbaaa.restOrderingProducts.controller;

import com.qbaaa.restOrderingProducts.model.OrderModel;
import com.qbaaa.restOrderingProducts.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping("/make")
    public ResponseEntity<String> make(@RequestBody List<Long> productsId) {

        double sumPrice = orderService.make( productsId );
        return new ResponseEntity<>("Total order amount " + sumPrice, HttpStatus.OK);
    }

    @GetMapping("/periodDate")
    public ResponseEntity<String> getPeriodDate(@NotNull @RequestParam(value = "start") LocalDate start,
                                                @NotNull @RequestParam(value = "end") LocalDate end) {

        List<OrderModel> orders = orderService.getPeriodDate(start, end);

        if ( !orders.isEmpty() ) {

            return new ResponseEntity<>( orders.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("In database does not have ordering between a given period.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updatePrice")
    public ResponseEntity<String> updateTotalPrice(@NotNull @RequestParam(value = "id") Long id) {

        if( orderService.calculate(id) ) {

            return new ResponseEntity<>("Order for id: " +id + " updated.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Calculate ordering failed.", HttpStatus.NOT_MODIFIED);
    }
}
