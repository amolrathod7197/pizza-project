package com.example.demo.controller;

import com.example.demo.model.Pizza;
import com.example.demo.response.PizzaResponse;
import com.example.demo.services.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class PizzaController {
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    @Autowired
    PizzaService pizzaService;

    @GetMapping("/pizzas")
    public PizzaResponse getAllPizza() {
        log.info("Received request to get list of Pizza's");
        return pizzaService.getAllPizzas();
    }

    @PostMapping("/pizza")
    public PizzaResponse createPizza(@Validated @RequestBody Pizza pizza){
        log.info("Received request to create pizza");
        return pizzaService.createPizza(pizza);
    }

    @GetMapping("/pizza/{id}")
    public PizzaResponse findPizzaById(@PathVariable Integer id) {
        log.info("Received request to get pizza info");
        return pizzaService.getPizzaById(id);
    }

    @PutMapping("/pizza/{id}")
    public PizzaResponse updatePizza(@PathVariable Integer id,@Validated @RequestBody Pizza pizzaDetails) {
        log.info("Received request to update pizza info");
        return pizzaService.updatePizza(id,pizzaDetails);
    }

    @DeleteMapping("/pizza/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        log.info("Received request to delete pizza info");
        return pizzaService.deletePizza(id);
    }

}
