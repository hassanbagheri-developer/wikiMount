package com.example.hassan.service;

import com.example.hassan.config.jwt.entity.Authority;
import com.example.hassan.config.jwt.entity.Role;
import com.example.hassan.dal.rpository.AuthorityRepository;
import com.example.hassan.dal.rpository.RoleRepository;
import com.example.hassan.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final MessageSource messageSource;


    private final AuthorityRepository authorityRepository;

    @Transactional
    public Role addAuthoritiesToRole(String roleName, Set<String> authorityNames) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new NotFoundException("role.not.found", messageSource));

        Set<Authority> authoritiesToAdd = new HashSet<>();

        for (String authorityName : authorityNames) {
            // Check if the authority already exists in the role
            boolean existsInRole = role.getAuthorities().stream()
                    .anyMatch(auth -> auth.getName().equals(authorityName));

            if (!existsInRole) {
                // Find or create the authority by name
                Authority authority = authorityRepository.findByName(authorityName)
                        .orElseGet(() -> {
                            Authority newAuthority = new Authority();
                            newAuthority.setName(authorityName);
                            return authorityRepository.save(newAuthority);
                        });
                authoritiesToAdd.add(authority);
            }
        }

        role.getAuthorities().addAll(authoritiesToAdd);
        return roleRepository.save(role);
    }
}
