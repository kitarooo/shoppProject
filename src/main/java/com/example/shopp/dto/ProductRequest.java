package com.example.shopp.dto;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductRequest {
    private String productName;
    private Double productPrice;
    private String description;
}
