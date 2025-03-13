package com.megacity.cab.service;


import com.megacity.cab.dto.CustomerDTO;
import com.megacity.cab.model.Customer;
import com.megacity.cab.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Convert Entity to DTO
    private CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(customer.getNic(), customer.getName(), customer.getAddress(), customer.getPhone());
    }

    // Convert DTO to Entity
    private Customer convertToEntity(CustomerDTO dto) {
        return new Customer(dto.getNic(), dto.getName(), dto.getAddress(), dto.getPhone());
    }

    // Get all customers
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get customer by NIC
    public Optional<CustomerDTO> getCustomerByNic(String nic) {
        return customerRepository.findById(nic).map(this::convertToDTO);
    }

    // Add a new customer
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        return convertToDTO(customerRepository.save(customer));
    }

    // Update an existing customer
    public CustomerDTO updateCustomer(String nic, CustomerDTO customerDetails) {
        return customerRepository.findById(nic).map(existingCustomer -> {
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setAddress(customerDetails.getAddress());
            existingCustomer.setPhone(customerDetails.getPhone());
            return convertToDTO(customerRepository.save(existingCustomer));
        }).orElse(null);
    }

    // Delete customer by NIC
    public void deleteCustomer(String nic) {
        customerRepository.deleteById(nic);
    }
}
