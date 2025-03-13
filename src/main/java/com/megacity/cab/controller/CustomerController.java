package com.megacity.cab.controller;



import com.megacity.cab.dto.CustomerDTO;
import com.megacity.cab.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend requests
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //  Get all customers
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //  Get customer by NIC
    @GetMapping("/{nic}")
    public ResponseEntity<CustomerDTO> getCustomerByNic(@PathVariable String nic) {
        Optional<CustomerDTO> customer = customerService.getCustomerByNic(nic);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  Add a new customer (with validation)
    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerService.addCustomer(customerDTO);
        return ResponseEntity.ok(savedCustomer);
    }

    //  Update customer by NIC
    @PutMapping("/{nic}")
    public ResponseEntity<?> updateCustomer(@PathVariable String nic, @Valid @RequestBody CustomerDTO customerDetails) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(nic, customerDetails);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    //  Delete customer by NIC
    @DeleteMapping("/{nic}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String nic) {
        customerService.deleteCustomer(nic);
        return ResponseEntity.noContent().build();
    }
}

