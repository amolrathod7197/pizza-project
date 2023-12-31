package com.example.demo.dao;

import com.example.demo.model.Pizza;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PizzaDao {

    List<Pizza> findAll();

    Optional<Pizza> findById(Integer id);

    int deleteById(Integer id);

    int insert(Pizza pizza);

    int update(Pizza pizza);

    Optional<Pizza> findByName(String name);
}
