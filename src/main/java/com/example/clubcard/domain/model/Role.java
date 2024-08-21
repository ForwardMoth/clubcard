package com.example.clubcard.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
