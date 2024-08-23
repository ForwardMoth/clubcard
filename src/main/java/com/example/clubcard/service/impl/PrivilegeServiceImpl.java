package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.request.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.domain.entity.Privilege;
import com.example.clubcard.domain.enums.PrivilegeEnum;
import com.example.clubcard.domain.mapper.PrivilegeMapper;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.PrivilegeErrorMessage;
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

    public Privilege getByName(String name){
        return privilegeRepository.findByName(PrivilegeEnum.STANDARD.getName()).orElseThrow(
                () -> new CustomException(PrivilegeErrorMessage.PRIVILEGE_NOT_FOUND.getMsg(),
                        PrivilegeErrorMessage.PRIVILEGE_NOT_FOUND.getStatus())
        );
    }

    public Privilege findById(Long id){
        return privilegeRepository.findById(id).orElseThrow(
                () -> new CustomException(PrivilegeErrorMessage.PRIVILEGE_NOT_FOUND.getMsg(),
                        PrivilegeErrorMessage.PRIVILEGE_NOT_FOUND.getStatus())
        );
    }

    public List<PrivilegeResponse> getAllPrivileges(){
        return privilegeRepository.findAll().stream().map(privilegeMapper::toDto).toList();
    }

    public PrivilegeResponse getPrivilege(Long id){
        return privilegeMapper.toDto(findById(id));
    }

    public PrivilegeResponse createPrivilege(PrivilegeRequest request){
        if(privilegeRepository.existsByName(request.getName())){
            throw new CustomException(PrivilegeErrorMessage.PRIVILEGE_EXISTS.getMsg(),
                    PrivilegeErrorMessage.PRIVILEGE_EXISTS.getStatus());
        }
        Privilege privilege = privilegeMapper.toEntity(request);
        privilegeRepository.save(privilege);
        return privilegeMapper.toDto(privilege);
    }
}
