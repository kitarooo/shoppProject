package com.example.shopp.dto;

import com.example.shopp.dto.Enums.Role;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
@Getter
@Setter
public class UserInfo {
    private Role role;
    private String first_name;;
    private String last_name;
    private int phone_number;
}
