package com.example.clubcard.service.impl;

import com.example.clubcard.domain.entity.Privilege;
import com.example.clubcard.domain.enums.PrivilegeEnum;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.UserErrorMessage;
import com.example.clubcard.repository.PrivilegeRepository;
import com.example.clubcard.service.PrivilegeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    public Privilege getByName(String name){
        return privilegeRepository.findByName(PrivilegeEnum.STANDARD.getName()).orElseThrow(
                () -> new CustomException(UserErrorMessage.PRIVILEGE_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND)
        );
    }
}
