package com.example.shopp.dto.Enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    USER("USER");

    final String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
