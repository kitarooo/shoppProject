package com.example.shopp.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class UserInfo {
    private String first_name;;
    private String last_name;
    private int phone_number;
}
