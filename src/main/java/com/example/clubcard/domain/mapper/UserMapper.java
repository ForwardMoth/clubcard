package com.example.clubcard.domain.mapper;

import com.example.clubcard.domain.dto.request.SignUpRequest;
import com.example.clubcard.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toEntity(SignUpRequest signUpRequest);
}
