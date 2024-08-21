package com.example.clubcard.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="plastic_card")
public class PlasticCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATE")
    private Date createdAt;

    @OneToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "card_type_id", referencedColumnName = "id") // , referencedColumnName = "id"
    private CardType cardType;
}
