package com.example.shopp.dto.request;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductRequest {
    private String uniqueCode;
}
