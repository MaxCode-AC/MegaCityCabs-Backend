package com.megacity.cab.dto;

import com.megacity.cab.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String username;
    private String password;
    private Role role;  // CUSTOMER or ADMIN
}
