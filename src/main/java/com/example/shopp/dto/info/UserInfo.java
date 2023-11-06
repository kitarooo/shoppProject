package com.example.shopp.dto.info;

import com.example.shopp.dto.Enums.Role;
import com.example.shopp.entity.Category;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserInfo {
    private Role role;
    private String firstName;;
    private String lastName;
    private int phoneNumber;
}
