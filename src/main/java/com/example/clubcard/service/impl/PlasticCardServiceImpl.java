package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.request.plastic_card.PlasticCardRequest;
import com.example.clubcard.domain.dto.response.plastic_card.PlasticCardResponse;
import com.example.clubcard.domain.entity.PlasticCard;
import com.example.clubcard.domain.entity.User;
import com.example.clubcard.domain.enums.PlasticCardEnum;
import com.example.clubcard.domain.mapper.PlasticCardMapper;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.PlasticCardErrorMessage;
import com.example.clubcard.repository.PlasticCardRepository;
import com.example.clubcard.service.CardTypeService;
import com.example.clubcard.service.PlasticCardService;
import com.example.clubcard.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Transactional
@RequiredArgsConstructor
public class PlasticCardServiceImpl implements PlasticCardService {
    private final PlasticCardRepository plasticCardRepository;
    private final UserService userService;
    private final CardTypeService cardTypeService;
    private final PlasticCardMapper plasticCardMapper;

    public PlasticCard create(Long userId, PlasticCardRequest request) {
        PlasticCard plasticCard = new PlasticCard();
        plasticCard.setUser(userService.findById(userId));
        plasticCard.setCardType(cardTypeService.findById(request.getCardTypeId()));
        plasticCard.setCreatedAt(new Date());
        plasticCard.setStatus(PlasticCardEnum.PROGRESS.name());
        return plasticCardRepository.save(plasticCard);
    }

    public PlasticCard findByUserId(Long userId){
        return plasticCardRepository.findByUserId(userId).orElseThrow(
                () -> new CustomException(
                        PlasticCardErrorMessage.PLASTIC_CARD_TYPE_NOT_FOUND.getMsg(),
                        PlasticCardErrorMessage.PLASTIC_CARD_TYPE_NOT_FOUND.getStatus()
                )
        );
    }

    @Override
    public PlasticCardResponse createCard(Long id, PlasticCardRequest request) {
        if (plasticCardRepository.existsByUserId(id)) {
            throw new CustomException(
                    PlasticCardErrorMessage.PLASTIC_CARD_TYPE_EXISTS.getMsg(),
                    PlasticCardErrorMessage.PLASTIC_CARD_TYPE_EXISTS.getStatus()
            );
        }
        return plasticCardMapper.toDto(create(id, request));
    }

    @Override
    public PlasticCardResponse updateCard(Long id, PlasticCardRequest request) {
        deleteCard(id);
        User user = userService.findById(id);
        user.setUUID();
        userService.save(user);
        return plasticCardMapper.toDto(create(id, request));
    }

    @Override
    public PlasticCardResponse updateStatusCard(Long id) {
        PlasticCard plasticCard = findByUserId(id);
        if (plasticCard.getStatus().equals(PlasticCardEnum.READY.name())){
            throw new CustomException(
                    PlasticCardErrorMessage.CANT_UPDATE_READY_PLASTIC_CARD.getMsg(),
                    PlasticCardErrorMessage.CANT_UPDATE_READY_PLASTIC_CARD.getStatus()
            );
        }
        plasticCard.setStatus(PlasticCardEnum.READY.name());
        return plasticCardMapper.toDto(plasticCardRepository.save(plasticCard));
    }

    @Override
    public void deleteCard(Long id) {
        PlasticCard plasticCard = findByUserId(id);
        plasticCardRepository.delete(plasticCard);
    }
}
