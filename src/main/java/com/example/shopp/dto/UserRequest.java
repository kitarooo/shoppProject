package com.example.shopp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserRequest {
    private String password;
    private String email;
}
