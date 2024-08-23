package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.domain.entity.Privilege;
import com.example.clubcard.domain.enums.PrivilegeEnum;
import com.example.clubcard.domain.mapper.PrivilegeMapper;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.UserErrorMessage;
import com.example.clubcard.repository.PrivilegeRepository;
import com.example.clubcard.service.PrivilegeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;

    public Privilege getByName(String name){
        return privilegeRepository.findByName(PrivilegeEnum.STANDARD.getName()).orElseThrow(
                () -> new CustomException(UserErrorMessage.PRIVILEGE_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND)
        );
    }

    public Privilege findById(Long id){
        return privilegeRepository.findById(id).orElseThrow(
                () -> new CustomException(UserErrorMessage.PRIVILEGE_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND)
        );
    }

    public List<PrivilegeResponse> getAllPrivileges(){
        return privilegeRepository.findAll().stream().map(privilegeMapper::toDto).toList();
    }

    public PrivilegeResponse getPrivilege(Long id){
        return privilegeMapper.toDto(findById(id));
    }
}
