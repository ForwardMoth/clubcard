package com.example.clubcard.repository;

import com.example.clubcard.domain.entity.PlasticCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlasticCardRepository extends JpaRepository<PlasticCard, Long> {
    Boolean existsByUserId(Long userId);

    Optional<PlasticCard> findByUserId(Long userId);
}
