package com.example.shopp.service;

import com.example.shopp.entity.Role;
import com.example.shopp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(){
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN").get();
        return roleAdmin;
    }
}
