package com.example.clubcard.service;

import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.domain.entity.Privilege;

import java.util.List;

public interface PrivilegeService {
    Privilege getByName(String name);

    Privilege findById(Long id);

    List<PrivilegeResponse> getAllPrivileges();

    PrivilegeResponse getPrivilege(Long id);
}
