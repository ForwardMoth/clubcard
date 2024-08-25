package com.example.clubcard.service;

import com.example.clubcard.domain.dto.page.PageDto;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardFilterRequest;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardRequest;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardResponse;
import com.example.clubcard.domain.entity.PlasticCard;
import org.springframework.data.domain.Page;

public interface PlasticCardService {
    PlasticCard create(Long userId, PlasticCardRequest request);

    PlasticCard findByUserId(Long userId);

    PlasticCardResponse createCard(Long id, PlasticCardRequest request);

    PlasticCardResponse updateCard(Long id, PlasticCardRequest request);

    PlasticCardResponse updateStatusCard(Long id);

    void deleteCard(Long id);

    Page<PlasticCardResponse> getAllPlasticCards(PageDto pageDto, PlasticCardFilterRequest plasticCardFilterRequest);

    PlasticCardResponse getPlasticCard(Long id);
}
