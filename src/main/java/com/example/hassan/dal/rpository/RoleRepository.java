package com.example.hassan.dal.rpository;

import com.example.hassan.config.jwt.entity.Role;
import com.example.hassan.config.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role>  findByName(String username);
}
