package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.response.OrderResponse;
import com.example.demo.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;


    @GetMapping("/orders")
    public OrderResponse getAllOrders() {
        log.info("Received request to get orders");
        return orderService.getAllOrders();
    }

    @PostMapping("/order")
    public OrderResponse createOrder(@RequestBody Order order){
        log.info("Received request to create orders");
        return orderService.createOrder(order);
    }

    @GetMapping("/order/{id}")
    public OrderResponse findOrderById(@PathVariable Integer id) {
        log.info("Received request to get order details for id:{}",id);
        return orderService.getOrderById(id);
    }

    @PutMapping("/order/{id}")
    public OrderResponse updateOrder(@PathVariable Integer id,
                                            @RequestBody Order orderDetails) {
        log.info("Received request to update order details for id:{}",id);
        return orderService.updateOrder(id,orderDetails);
    }

    @DeleteMapping("/order/{id}")
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public ResponseEntity deleteUser
            (@PathVariable Integer id) {
        log.info("Received request to delete order for id:{}",id);
        return orderService.deleteOrder(id);

    }

}
