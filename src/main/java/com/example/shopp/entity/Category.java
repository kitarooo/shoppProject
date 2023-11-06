package com.example.shopp.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("category_id")
    private Long categoryId;

    @Column(unique = true, name = "category_name")
    private String categoryName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;
}
