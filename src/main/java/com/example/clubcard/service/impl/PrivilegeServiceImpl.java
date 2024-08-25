package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.privilege.PrivilegeResponse;
import com.example.clubcard.domain.entity.Privilege;
import com.example.clubcard.domain.enums.PrivilegeEnum;
import com.example.clubcard.domain.mapper.PrivilegeMapper;
import com.example.clubcard.exception.message.PrivilegeErrorMessage;
import com.example.clubcard.exception.type.BadRequestException;
import com.example.clubcard.exception.type.NotFoundException;
import com.example.clubcard.repository.PrivilegeRepository;
import com.example.clubcard.service.PrivilegeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;

    public Privilege getByName(String name) {
        return privilegeRepository.findByName(PrivilegeEnum.STANDARD.getName()).orElseThrow(
                () -> new NotFoundException(PrivilegeErrorMessage.PRIVILEGE_NOT_FOUND.getName())
        );
    }

    public Privilege findById(Long id) {
        return privilegeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(PrivilegeErrorMessage.PRIVILEGE_NOT_FOUND.getName())
        );
    }

    public List<PrivilegeResponse> getAllPrivileges() {
        return privilegeRepository.findAll().stream().map(privilegeMapper::toDto).toList();
    }

    public PrivilegeResponse getPrivilege(Long id) {
        return privilegeMapper.toDto(findById(id));
    }

    public PrivilegeResponse createPrivilege(PrivilegeRequest request) {
        if (privilegeRepository.existsByName(request.getName())) {
            throw new BadRequestException(PrivilegeErrorMessage.PRIVILEGE_EXISTS.getName());
        }
        Privilege privilege = privilegeMapper.toEntity(request);
        privilegeRepository.save(privilege);
        return privilegeMapper.toDto(privilege);
    }

    public PrivilegeResponse updatePrivilege(Long id, PrivilegeRequest request) {
        Privilege privilege = findById(id);
        privilegeMapper.updatePrivilegeFromDto(request, privilege);
        privilegeRepository.save(privilege);
        return privilegeMapper.toDto(privilege);
    }

    public void deletePrivilege(Long id) {
        Privilege privilege = findById(id);

        if (privilege.getUsers().size() > 0) {
            throw new BadRequestException(PrivilegeErrorMessage.CANT_DELETE_PRIVILEGE.getName());
        }

        privilegeRepository.delete(privilege);
    }
}
