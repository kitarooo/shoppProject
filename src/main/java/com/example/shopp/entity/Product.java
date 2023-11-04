package com.example.shopp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String product_name;
    @Column(nullable = false)
    private double product_price;
    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
