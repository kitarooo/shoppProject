package com.example.shopp.repository;

import com.example.shopp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.OptionPaneUI;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllById(Long id);

    Optional<User> findUserByEmail(String email);
}
