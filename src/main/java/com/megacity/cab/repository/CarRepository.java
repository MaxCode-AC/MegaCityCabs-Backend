package com.megacity.cab.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import com.megacity.cab.model.Car;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByModelAndPrice(@NotBlank(message = "Car model is required") String carModel,
                                      @NotNull(message = "Price is required") Double price);
}
