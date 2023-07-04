package com.example.demo.controller;


import com.example.demo.model.Customer;
import com.example.demo.response.CustomerResponse;
import com.example.demo.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public CustomerResponse getAllCustomers() {
        log.info("Received request to get pizza info");
        return customerService.getAllCustomers();
    }

    @PostMapping("/customer")
    public CustomerResponse createCustomer(@Validated @RequestBody Customer customer) {
        log.info("Received request to create customer");
        return customerService.createCustomer(customer);
    }

    @GetMapping("/customer/{id}")
    public CustomerResponse findCustomerById(@PathVariable Integer id) {
        log.info("Received request to get customer for id:{}", id);
        return customerService.getCustomerById(id);
    }

    @PutMapping("/customer/{id}")
    public CustomerResponse updateCustomer(@PathVariable Integer id,
                                           @RequestBody Customer customerDetails) {
        log.info("Received request to update customer for id:{}", id);
        return customerService.updateCustomer(id, customerDetails);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id) {
        log.info("Received request to delete customer for id:{}", id);
        return customerService.deleteCustomer(id);
    }

}
