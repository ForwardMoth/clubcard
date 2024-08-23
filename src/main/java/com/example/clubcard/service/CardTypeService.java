package com.example.clubcard.service;

import com.example.clubcard.domain.dto.request.card_type.CardTypeRequest;
import com.example.clubcard.domain.dto.response.card_type.CardTypeResponse;
import com.example.clubcard.domain.entity.CardType;

import java.util.List;

public interface CardTypeService {
    CardType findById(Long id);

    CardTypeResponse getCardType(Long id);

    List<CardTypeResponse> getAllCardTypes();

    CardTypeResponse createCardType(CardTypeRequest request);

    CardTypeResponse updateCardType(Long id, CardTypeRequest request);

    void deleteCardType(Long id);
}
