package com.example.demo.services;

import com.example.demo.Constants;
import com.example.demo.Utility;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.PizzaDao;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.response.OrderDetail;
import com.example.demo.response.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private PizzaDao pizzaDao;

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public OrderResponse getAllOrders() {
        OrderResponse response = new OrderResponse();
        response.setOrderList(orderDao.findAll());
        response.setMessage("Fetched list of orders successfully");
        response.setStatus(Constants.SUCCESS_STATUS);
        log.info("Returning response after processing request");
        return response;
    }

    @Transactional
    public OrderResponse createOrder(Order order) {
        customerDao.findById(order.getCustomerId()).
                orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + order.getCustomerId()));
        order.setOrderDateTime(Utility.getUTCFormattedDate());
        List<OrderDetail> pizzaList = order.getPizzaList();
        processOrderValidation(pizzaList);
        int totalAmount = pizzaList.stream().filter(o -> o.getAmount() > 0).mapToInt(o -> o.getAmount()).sum();//return from method
        order.setAmount(totalAmount);
        log.info("Started inserting entry in orders(Parent table)..");
        orderDao.insertIntoOrders(order);
        log.info("Started inserting entry in carts for orderId:{} ", order.getOrderId());
        orderDao.insertIntoCart(order.getOrderId(), pizzaList);
        orderDao.updateQuantityForItem(pizzaList);
        return prepareOrderResponse(order,pizzaList);
    }

    public OrderResponse updateOrder(Integer id, Order order) {
        orderDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Order not exist with id :" + id));
        order.setOrderId(id);
        order.setOrderDateTime(Utility.getUTCFormattedDate());
        List<OrderDetail> pizzaList = order.getPizzaList();
        processOrderValidation(pizzaList);
        int totalAmount = pizzaList.stream().filter(o -> o.getAmount() > 0).mapToInt(o -> o.getAmount()).sum();
        order.setAmount(totalAmount);
        log.info("Started updating entry in orders(Parent table)..");
        orderDao.updateOrders(order);
        log.info("deleting old cart entries for orderId:{}", order.getOrderId());
        orderDao.cleanOldCart(order.getOrderId());
        log.info("Started updating entry in cart for items..");
        orderDao.insertIntoCart(order.getOrderId(), pizzaList);
        return prepareOrderResponse(order,pizzaList);
    }

    private OrderResponse prepareOrderResponse(Order order, List<OrderDetail> pizzaList) {
        OrderResponse response = new OrderResponse();
        response.setStatus(Constants.SUCCESS_STATUS);
        response.setMessage("Order is successfully placed with below details");
        Order orderResponse = new Order();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setCustomerId(order.getCustomerId());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setOrderDateTime(order.getOrderDateTime());
        orderResponse.setPizzaList(pizzaList);
        orderResponse.setAmount(order.getAmount());
        response.setOrder(orderResponse);
        log.info("Returning response after processing request");
        return response;
    }


    private void processOrderValidation(List<OrderDetail> pizzaList) {
        for (OrderDetail p : pizzaList) {
            pizzaDao.findById(p.getPizzaId()).
                    orElseThrow(() -> new ResourceNotFoundException("Pizza not exist with id :" + p.getPizzaId()));
            Map<String, Integer> m = orderDao.checkQuantityAndPrice(p.getSize(), p.getPizzaId());
            if (m.get("available_quantity") < p.getQuantity()) {
                throw new BadRequestException("quantity is not available for selected pizza id:" + p.getPizzaId() + "");
            }
            p.setAmount(m.get("price") * p.getQuantity());
        }
    }

    public OrderResponse getOrderById(Integer id) {
        Order order = orderDao.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not exist with id :" + id));
        OrderResponse response = new OrderResponse();
        response.setStatus(Constants.SUCCESS_STATUS);
        response.setMessage("Fetched details of Order with id :" + id);
        response.setOrder(order);
        log.info("Returning response after processing request");
        return response;
    }


    public ResponseEntity deleteOrder(Integer id) {
        boolean isDeleted = orderDao.deleteById(id) > 0;
        if (!isDeleted) {
            throw new ResourceNotFoundException("Order not exist with id :" + id);
        }
        log.info("Order deleted successfully..!");
        return ResponseEntity.noContent().build();
    }
}
