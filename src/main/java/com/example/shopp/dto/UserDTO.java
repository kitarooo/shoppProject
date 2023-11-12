package com.example.shopp.dto;

import com.example.shopp.dto.Enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {
    private String email;
    private String password;
    private String firstName;;
    private String lastName;
    private int phoneNumber;
    private Role role;
}
