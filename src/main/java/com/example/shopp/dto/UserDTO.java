package com.example.shopp.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String password;
    private String firstName;;
    private String lastName;
    private int phoneNumber;
}
