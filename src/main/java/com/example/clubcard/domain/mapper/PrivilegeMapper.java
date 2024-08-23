package com.example.clubcard.domain.mapper;

import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.domain.entity.Privilege;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {
    PrivilegeResponse toDto(Privilege privilege);

    Privilege toEntity(PrivilegeResponse privilegeResponse);

    List<PrivilegeResponse> toDto(List<Privilege> privileges);
}
