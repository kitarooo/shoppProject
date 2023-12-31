package com.example.shopp.repository;

import com.example.shopp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByUserId(Long id);

    Optional<User> findUserByEmail(String email);
}
