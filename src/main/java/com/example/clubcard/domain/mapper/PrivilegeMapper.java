package com.example.clubcard.domain.mapper;

import com.example.clubcard.domain.dto.request.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.request.user.UserUpdateRequest;
import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.domain.entity.Privilege;
import com.example.clubcard.domain.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {
    PrivilegeResponse toDto(Privilege privilege);

    Privilege toEntity(PrivilegeRequest privilegeRequest);

    List<PrivilegeResponse> toDto(List<Privilege> privileges);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePrivilegeFromDto(PrivilegeRequest request, @MappingTarget Privilege privilege);
}
