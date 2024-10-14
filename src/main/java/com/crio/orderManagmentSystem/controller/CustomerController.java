package com.crio.orderManagmentSystem.controller;

import com.crio.orderManagmentSystem.dto.UserDTO;
import com.crio.orderManagmentSystem.model.Customer;
import com.crio.orderManagmentSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Get All Customers
    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    // Get Customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    // Save a Customer
    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody UserDTO userDTO) {
        Customer savedCustomer = customerService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    // Delete a Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Customer deleted successfully.");
    }
}
