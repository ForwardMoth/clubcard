package com.example.clubcard.service;

import com.example.clubcard.domain.dto.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.privilege.PrivilegeResponse;
import com.example.clubcard.domain.entity.Privilege;

import java.util.List;

public interface PrivilegeService {
    Privilege getByName(String name);

    Privilege findById(Long id);

    List<PrivilegeResponse> getAllPrivileges();

    PrivilegeResponse getPrivilege(Long id);

    PrivilegeResponse createPrivilege(PrivilegeRequest request);

    PrivilegeResponse updatePrivilege(Long id, PrivilegeRequest request);

    void deletePrivilege(Long id);
}
