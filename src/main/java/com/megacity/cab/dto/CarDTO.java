package com.megacity.cab.dto;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarDTO {
    private Long id;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Number is required")
    private String number;

    @NotBlank(message = "Driver name is required")
    private String driverName;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a positive value")
    private Double price;
}
