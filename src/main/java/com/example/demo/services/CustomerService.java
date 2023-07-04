package com.example.demo.services;

import com.example.demo.Constants;
import com.example.demo.dao.CustomerDao;
import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Pizza;
import com.example.demo.response.CustomerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    public CustomerResponse getAllCustomers() {
        CustomerResponse response = new CustomerResponse();
        response.setCustomerList(customerDao.findAll());
        response.setMessage("Fetched list of Customers successfully");
        response.setStatus(Constants.SUCCESS_STATUS);
        log.info("Returning response after request execution...");
        return response;
    }

    public CustomerResponse createCustomer(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        Optional<Pizza> pizzaEntry = customerDao.findByMobile(customer.getPhoneNumber());
        if (pizzaEntry.isPresent()) {
            throw new DuplicateResourceException("Customer is already present..!");
        }
        response.setStatus(customerDao.insert(customer) > 0 ? Constants.SUCCESS_STATUS : Constants.FAILED_STATUS);
        if (response.getStatus().equals(Constants.SUCCESS_STATUS)) {
            response.setMessage(Constants.CUST_ADDED_SUCCESS_MSG);
            response.setCustomer(customer);

        } else {
            response.setMessage(Constants.CUST_ADDED_FAIL_MSG);
        }
        log.info("Returning response after request execution...");
        return response;
    }

    public CustomerResponse getCustomerById(Integer id) {
        Customer customer = customerDao.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
        CustomerResponse response = new CustomerResponse();
        response.setStatus(Constants.SUCCESS_STATUS);
        response.setMessage("Fetched details of Customer with id :" + id);
        response.setCustomer(customer);
        log.info("Returning response after request execution...");
        return response;
    }

    public CustomerResponse updateCustomer(Integer id, Customer customerDetails) {
        Customer customer = customerDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Customer not exist with id :" + id));
        customerDetails.setCustomerId(id);
        CustomerResponse response = new CustomerResponse();

        response.setStatus(customerDao.update(customerDetails) > 0 ? Constants.SUCCESS_STATUS : Constants.FAILED_STATUS);
        if (response.getStatus().equals(Constants.SUCCESS_STATUS)) {
            response.setMessage("Customer Updated successfully..!");
            response.setCustomer(customerDetails);
        } else {
            response.setMessage("Failed to update Customer details");
        }
        log.info("Returning response after request execution...");
        return response;
    }

    public ResponseEntity deleteCustomer(Integer id) {
        Customer customer = customerDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Customer not exist with id :" + id));
        Boolean bool = customerDao.deleteById(customer.getCustomerId()) > 0;
        if (bool) {
            log.info("Customer deleted successfully with id:{}", id);
        }
        return ResponseEntity.noContent().build();
    }
}
