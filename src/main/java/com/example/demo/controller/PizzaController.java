package com.example.demo.controller;

import com.example.demo.dao.PizzaDao;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PizzaController {
    @Autowired
    private PizzaDao pizzaDao;

    @GetMapping("/pizza")
    public List<Pizza> getAllPizza()
    {
        return pizzaDao.findAll();
    }

    @PostMapping("/pizza")
    public Map<String, Boolean> createPizza(@RequestBody Pizza pizza)  {

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = pizzaDao.insert(pizza) > 0 ?
                response.put("created", Boolean.TRUE) :
                response.put("created", Boolean.FALSE);

        return response;

    }

    @GetMapping("/pizza/{id}")
    public Pizza findPizzaById(@PathVariable Integer id) {

        Pizza pizza = pizzaDao.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Pizza not exist with id :" + id));
        return pizza;
    }

    @PutMapping("/pizza/{id}")
    public Map<String, Boolean> updatePizza(@PathVariable Integer id,
                                           @RequestBody Pizza pizzaDetails) {

        Pizza pizza = pizzaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Pizza not exist with id :" + id));
        pizzaDetails.setId(id);
        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = pizzaDao.update(pizzaDetails) > 0 ?
                response.put("updated", Boolean.TRUE) :
                response.put("updated", Boolean.FALSE);

        return response;
    }

    @DeleteMapping("/pizza/{id}")
    public Map<String, Boolean> deleteUser
            (@PathVariable Integer id) {

        Pizza pizza = pizzaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Pizza not exist with id :" + id));

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = pizzaDao.deleteById(pizza.getId()) > 0 ?
                response.put("deleted", Boolean.TRUE) :
                response.put("deleted", Boolean.FALSE);
        return response;
    }

}
