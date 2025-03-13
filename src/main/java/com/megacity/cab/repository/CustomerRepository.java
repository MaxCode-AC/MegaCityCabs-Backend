package com.megacity.cab.repository;



import com.megacity.cab.model.Customer;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByNic(@NotBlank(message = "Customer NIC is required") String customerNic);
    // NIC is the primary key (String type)
}
