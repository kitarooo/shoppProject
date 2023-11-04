package com.example.shopp.entity;

import com.example.shopp.entity.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
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
    private String email;
    @Column(unique = true)
    private String password;
    @Embedded
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Order> orders;
}
