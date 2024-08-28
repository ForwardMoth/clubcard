package com.example.clubcard.domain.mapper;

import com.example.clubcard.domain.dto.plastic_card.PlasticCardResponse;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardUserResponse;
import com.example.clubcard.domain.entity.PlasticCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlasticCardMapper {

    @Mapping(source = "user", target = "userResponse")
    @Mapping(source = "user.id",target = "userResponse.userId")
    @Mapping(source = "user.privilege", target = "userResponse.privilegeResponse")
    @Mapping(source = "cardType", target = "cardTypeResponse")
    PlasticCardResponse toDto(PlasticCard plasticCard);

    @Mapping(source = "cardType", target = "cardTypeResponse")
    PlasticCardUserResponse toUserResponse(PlasticCard plasticCard);
}
