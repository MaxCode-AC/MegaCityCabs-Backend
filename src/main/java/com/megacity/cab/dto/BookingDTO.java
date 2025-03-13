package com.megacity.cab.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingDTO {

    @NotBlank(message = "Customer NIC is required")
    private String customerNic;

    @NotBlank(message = "Car model is required")
    private String carModel;

    @NotNull(message = "Price is required")
    private Double price;

    @NotBlank(message = "Origin is required")
    private String origin;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Distance is required")
    private Double distance;

    @NotNull(message = "Date is required")
    private LocalDateTime bookingDate;
}
