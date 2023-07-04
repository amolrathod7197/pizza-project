package com.example.demo.dao;

import com.example.demo.model.Customer;
import com.example.demo.model.Pizza;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);

    int deleteById(Integer id);

    int insert(Customer user);

    int update(Customer user);

    Optional<Pizza> findByMobile(Long phoneNumber);
}
