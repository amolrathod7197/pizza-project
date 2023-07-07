package com.example.demo.dao;

import com.example.demo.model.Order;
import com.example.demo.response.OrderDetail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderDao {

    List<Order> findAll();
    Optional<Order> findById(Integer id);
    int deleteById(Integer id);
    int insertIntoOrders(Order order);
    Map checkQuantityAndPrice(String size, Integer id);
    int update(Order order);
    int insertIntoCart(int orderId, List<OrderDetail> pizzaList);
    void updateAmountInOrder(int totalAmount, int orderId);

    LocalDateTime getOrderDateTime();

    int updateOrders(Order order);

    int updateCart(int orderId, int pizzaId, String size, int quantity, int amount);

    void cleanOldCart(int orderId);

    void updateQuantityForItem(List<OrderDetail> pizzaList);
}
