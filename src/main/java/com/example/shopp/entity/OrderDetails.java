package com.example.shopp.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("order_details_id")
    private Long orderDetailsId;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
