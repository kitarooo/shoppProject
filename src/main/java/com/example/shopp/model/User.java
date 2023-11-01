package com.example.shopp.model;

import com.example.shopp.model.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private int phone_number;
    @Column(unique = true)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Order> orders;
}
