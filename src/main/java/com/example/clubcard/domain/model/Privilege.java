package com.example.clubcard.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="price", nullable = false)
    @Min(0)
    private Integer price;

    @OneToMany(mappedBy = "privilege")
    private List<User> users;
}
