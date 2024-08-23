package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.request.card_type.CardTypeRequest;
import com.example.clubcard.domain.dto.response.card_type.CardTypeResponse;
import com.example.clubcard.domain.entity.CardType;
import com.example.clubcard.domain.mapper.CardTypeMapper;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.CardTypeErrorMessage;
import com.example.clubcard.repository.CardTypeRepository;
import com.example.clubcard.service.CardTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardTypeServiceImpl implements CardTypeService {
    private final CardTypeMapper cardTypeMapper;
    private final CardTypeRepository cardTypeRepository;

    @Override
    public CardType findById(Long id){
        return cardTypeRepository.findById(id).orElseThrow(
                () -> new CustomException(CardTypeErrorMessage.CARD_TYPE_EXISTS.getMsg(),
                        CardTypeErrorMessage.CARD_TYPE_EXISTS.getStatus())
        );
    }

    @Override
    public CardTypeResponse getCardType(Long id) {
        return cardTypeMapper.toDto(findById(id));
    }

    @Override
    public List<CardTypeResponse> getAllCardTypes() {
        return cardTypeRepository.findAll().stream().map(cardTypeMapper::toDto).toList();
    }

    @Override
    public CardTypeResponse createCardType(CardTypeRequest request) {
        if(cardTypeRepository.existsByName(request.getName())){
            throw new CustomException(CardTypeErrorMessage.CARD_TYPE_EXISTS.getMsg(),
                    CardTypeErrorMessage.CARD_TYPE_EXISTS.getStatus());
        }

        CardType cardType = cardTypeMapper.toEntity(request);
        cardTypeRepository.save(cardType);
        return cardTypeMapper.toDto(cardType);
    }

    @Override
    public CardTypeResponse updateCardType(Long id, CardTypeRequest request) {
        CardType cardType = findById(id);
        cardTypeMapper.updateCardTypeFromDto(request, cardType);
        cardTypeRepository.save(cardType);
        return cardTypeMapper.toDto(cardType);
    }

    @Override
    public void deleteCardType(Long id) {
        CardType cardType = findById(id);

        if (cardType.getPlasticCards().size() > 0){
            throw new CustomException(CardTypeErrorMessage.CARD_TYPE_EXISTS.getMsg(),
                    CardTypeErrorMessage.CARD_TYPE_EXISTS.getStatus());
        }

        cardTypeRepository.delete(cardType);
    }
}
