package com.example.clubcard.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="card_type")
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "cardType")
    private List<PlasticCard> plasticCards;
}
