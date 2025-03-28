package com.example.hassan.dal.rpository;

import com.example.hassan.config.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserApplicationRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}
