package com.example.clubcard.domain.mapper;

import com.example.clubcard.domain.dto.request.card_type.CardTypeRequest;
import com.example.clubcard.domain.dto.response.card_type.CardTypeResponse;
import com.example.clubcard.domain.entity.CardType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {
    CardTypeResponse toDto(CardType CardType);

    CardType toEntity(CardTypeRequest request);

    List<CardTypeResponse> toDto(List<CardType> cardTypes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCardTypeFromDto(CardTypeRequest request, @MappingTarget CardType cardType);
}
