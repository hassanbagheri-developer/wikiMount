package com.example.hassan.dal.entity;

import com.example.hassan.config.jwt.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private boolean expired;
    private boolean revoked;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationAt;
    @ManyToOne
    private User user;


}
