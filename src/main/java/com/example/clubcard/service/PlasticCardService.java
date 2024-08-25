package com.example.clubcard.service;

import com.example.clubcard.domain.dto.plastic_card.PlasticCardRequest;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardResponse;
import com.example.clubcard.domain.entity.PlasticCard;

public interface PlasticCardService {
    PlasticCard create(Long userId, PlasticCardRequest request);

    PlasticCard findByUserId(Long userId);

    PlasticCardResponse createCard(Long id, PlasticCardRequest request);

    PlasticCardResponse updateCard(Long id, PlasticCardRequest request);

    PlasticCardResponse updateStatusCard(Long id);

    void deleteCard(Long id);
}
