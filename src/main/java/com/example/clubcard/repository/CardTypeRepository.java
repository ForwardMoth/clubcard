package com.example.clubcard.repository;

import com.example.clubcard.domain.entity.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Long> {
    Boolean existsByName(String name);


}
