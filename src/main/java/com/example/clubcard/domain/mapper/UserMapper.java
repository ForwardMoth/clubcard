package com.example.clubcard.domain.mapper;

import com.example.clubcard.domain.dto.request.sign.SignUpRequest;
import com.example.clubcard.domain.dto.request.user.UserUpdateRequest;
import com.example.clubcard.domain.dto.response.user.UserBalanceResponse;
import com.example.clubcard.domain.dto.response.user.UserProfileResponse;
import com.example.clubcard.domain.dto.response.user.UserResponse;
import com.example.clubcard.domain.dto.response.user.UserStatusResponse;
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
    UserResponse toDto(User user);

    @Mapping(source = "privilege", target = "privilegeResponse")
    @Mapping(source = "id",target = "userId")
    UserStatusResponse toStatusResponse(User user);

    @Mapping(source = "id",target = "userId")
    UserBalanceResponse toBalanceResponce(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateRequest request, @MappingTarget User user);
}
