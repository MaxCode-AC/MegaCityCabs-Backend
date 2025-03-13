package com.megacity.cab.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO {

    @NotBlank(message = "NIC is required")
    @Pattern(regexp = "^(?:[0-9]{9}[VvXx]|[0-9]{12})$", message = "Invalid NIC format. Use 9 digits + V/X or 12 digits")
    private String nic;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(07[01245678])[0-9]{7}$", message = "Invalid Sri Lankan phone number")
    private String phone;

    // Constructors
    public CustomerDTO() {}

    public CustomerDTO(String nic, String name, String address, String phone) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters
    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
