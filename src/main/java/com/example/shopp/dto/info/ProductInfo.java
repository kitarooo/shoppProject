package com.example.shopp.dto.info;

import com.example.shopp.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductInfo {
    private String productName;
    private Double productPrice;
    private String description;
    private int quantity;
    private Category category;
}
