package com.example.demo.services;

import com.example.demo.Constants;
import com.example.demo.controller.PizzaController;
import com.example.demo.dao.PizzaDao;
import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Pizza;
import com.example.demo.response.PizzaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PizzaService {
    private static final Logger log = LoggerFactory.getLogger(PizzaService.class);
    @Autowired
    PizzaDao pizzaDao;


    public PizzaResponse getAllPizzas()
    {
        PizzaResponse response = new PizzaResponse();
        response.setPizzaList(pizzaDao.findAll());
        response.setMessage("Fetched list of Pizzas successfully");
        response.setStatus(Constants.SUCCESS_STATUS);
        log.info("Returning response after processing request.");
        return response;
    }

    public PizzaResponse createPizza(Pizza pizza) {
        Optional<Pizza> pizzaEntry = pizzaDao.findByName(pizza.getName());
        if(pizzaEntry.isPresent())
        {
            throw new DuplicateResourceException("Pizza is already present with same name");
        }
        PizzaResponse response = new PizzaResponse();
        response.setStatus(pizzaDao.insert(pizza) > 0 ? Constants.SUCCESS_STATUS : Constants.FAILED_STATUS);
        if (response.getStatus().equals(Constants.SUCCESS_STATUS)) {
            response.setMessage(Constants.PIZZA_ADDED_SUCCESS_MSG);
            response.setPizza(pizza);
        } else {
            response.setMessage(Constants.PIZZA_ADDED_FAIL_MSG);
        }
        log.info("Returning response after processing request.");
        return response;
    }

    public PizzaResponse getPizzaById(Integer id) {
        Pizza pizza = pizzaDao.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Pizza not exist with id :" + id));
        PizzaResponse response = new PizzaResponse();
        response.setStatus(Constants.SUCCESS_STATUS);
        response.setMessage("Fetched details of pizza with id :" + id);
        response.setPizza(pizza);
        log.info("Returning response after processing request.");
        return response;
    }

    public PizzaResponse updatePizza(Integer id, Pizza pizzaDetails) {
        PizzaResponse response = new PizzaResponse();
        Pizza pizza = pizzaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Pizza not exist with id :" + id));
        pizzaDetails.setId(id);

        response.setStatus(pizzaDao.update(pizzaDetails) > 0 ? Constants.SUCCESS_STATUS : Constants.FAILED_STATUS);
        if (response.getStatus().equals(Constants.SUCCESS_STATUS)) {
            response.setMessage("Pizza Updated successfully..!");
            response.setPizza(pizzaDetails);
        } else {
            response.setMessage("Failed to update pizza");
        }
        log.info("Returning response after processing request.");
        return response;
    }

    public ResponseEntity deletePizza(Integer id) {
        Pizza pizza = pizzaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Pizza not exist with id :" + id));
        PizzaResponse response = new PizzaResponse();
        pizzaDao.deleteById(pizza.getId());
        log.info("Returning response after processing request.");
        return ResponseEntity.noContent().build();
    }
}
