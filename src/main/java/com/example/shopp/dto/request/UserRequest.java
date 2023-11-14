package com.example.shopp.dto.request;

import com.example.shopp.dto.Enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserRequest {
    private String password;
    private String email;
    //private Role role;
}