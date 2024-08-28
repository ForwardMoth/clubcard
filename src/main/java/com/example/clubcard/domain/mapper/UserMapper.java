package com.example.clubcard.domain.mapper;

import com.example.clubcard.domain.dto.sign.SignUpRequest;
import com.example.clubcard.domain.dto.user.*;
import com.example.clubcard.domain.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses= {PrivilegeMapper.class})
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toEntity(SignUpRequest signUpRequest);

    @Mapping(source = "id",target = "userId")
    UserProfileResponse toProfileResponse(User user);

    @Mapping(source = "privilege", target = "privilegeResponse")
    @Mapping(source = "id",target = "userId")
    @Mapping(source = "plasticCard", target = "plasticCardUserResponse")
    @Mapping(source = "plasticCard.cardType", target = "plasticCardUserResponse.cardTypeResponse")
    UserResponse toDto(User user);

    @Mapping(source = "privilege", target = "privilegeResponse")
    @Mapping(source = "id",target = "userId")
    UserStatusResponse toStatusResponse(User user);

    @Mapping(source = "id",target = "userId")
    UserBalanceResponse toBalanceResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateRequest request, @MappingTarget User user);

    UserQrCodeResponse toQrCodeResponse(User user);
}
