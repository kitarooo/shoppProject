package com.example.shopp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Name("product_id")
    private Long productId;

    @NotNull
    @Column(unique = true, nullable = false, name = "product_name")
    private String productName;

    @NotNull
    @Column(nullable = false, name = "product_price")
    private Double productPrice;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Column
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}
