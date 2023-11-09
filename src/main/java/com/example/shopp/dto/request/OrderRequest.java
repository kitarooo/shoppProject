package com.example.shopp.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderRequest {
    private Long orderCode;
}
