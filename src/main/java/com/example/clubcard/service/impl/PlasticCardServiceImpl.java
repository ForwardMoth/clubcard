package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.page.PageDto;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardFilterRequest;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardRequest;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardResponse;
import com.example.clubcard.domain.entity.PlasticCard;
import com.example.clubcard.domain.entity.User;
import com.example.clubcard.domain.enums.PlasticCardEnum;
import com.example.clubcard.domain.mapper.PlasticCardMapper;
import com.example.clubcard.exception.message.PlasticCardErrorMessage;
import com.example.clubcard.exception.type.BadRequestException;
import com.example.clubcard.exception.type.NotFoundException;
import com.example.clubcard.repository.PlasticCardRepository;
import com.example.clubcard.repository.criteria.PlasticCardCriteriaRepository;
import com.example.clubcard.service.CardTypeService;
import com.example.clubcard.service.PlasticCardService;
import com.example.clubcard.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@Transactional
@RequiredArgsConstructor
public class PlasticCardServiceImpl implements PlasticCardService {
    private final PlasticCardRepository plasticCardRepository;
    private final UserService userService;
    private final CardTypeService cardTypeService;
    private final PlasticCardMapper plasticCardMapper;
    private final PlasticCardCriteriaRepository plasticCardCriteriaRepository;

    public PlasticCard create(Long userId, PlasticCardRequest request) {
        PlasticCard plasticCard = new PlasticCard();
        plasticCard.setUser(userService.findById(userId));
        plasticCard.setCardType(cardTypeService.findById(request.getCardTypeId()));
        plasticCard.setCreatedAt(Instant.now());
        plasticCard.setStatus(PlasticCardEnum.PROGRESS.name());
        return plasticCardRepository.save(plasticCard);
    }

    public PlasticCard findByUserId(Long userId) {
        return plasticCardRepository.findByUserId(userId).orElseThrow(() ->
                new NotFoundException(PlasticCardErrorMessage.PLASTIC_CARD_FOR_USER_NOT_FOUND.getName())
        );
    }

    @Override
    public PlasticCardResponse createCard(Long id, PlasticCardRequest request) {
        if (plasticCardRepository.existsByUserId(id)) {
            throw new BadRequestException(PlasticCardErrorMessage.PLASTIC_CARD_TYPE_EXISTS.getName());
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
        if (plasticCard.getStatus().equals(PlasticCardEnum.READY.name())) {
            throw new BadRequestException(PlasticCardErrorMessage.CANT_UPDATE_READY_PLASTIC_CARD.getName());
        }
        plasticCard.setStatus(PlasticCardEnum.READY.name());
        return plasticCardMapper.toDto(plasticCardRepository.save(plasticCard));
    }

    @Override
    public void deleteCard(Long id) {
        PlasticCard plasticCard = findByUserId(id);
        plasticCardRepository.delete(plasticCard);
    }

    @Override
    public Page<PlasticCardResponse> getAllPlasticCards(PageDto pageDto,
                                                        PlasticCardFilterRequest plasticCardFilterRequest) {
        return plasticCardCriteriaRepository
                .findAllWithFilters(pageDto, plasticCardFilterRequest)
                .map(plasticCardMapper::toDto);
    }

    @Override
    public PlasticCardResponse getPlasticCard(Long id) {
        return plasticCardMapper.toDto(findById(id));
    }

    private PlasticCard findById(Long id) {
        return plasticCardRepository.findById(id).orElseThrow(
                () -> new NotFoundException(PlasticCardErrorMessage.PLASTIC_CARD_NOT_FOUND.getName())
        );
    }
}
