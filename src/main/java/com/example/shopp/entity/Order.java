package com.example.shopp.entity;

import com.example.shopp.dto.Enums.MethodOfPurchases;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("order_id")
    private Long orderId;

    @Column
    private double totalPrice;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column
    private MethodOfPurchases methodOfPurchases;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetails> details;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;
}
