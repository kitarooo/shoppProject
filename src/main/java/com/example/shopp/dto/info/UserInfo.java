package com.example.shopp.dto.info;

import com.example.shopp.dto.Enums.Role;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserInfo {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private Role role;
}
