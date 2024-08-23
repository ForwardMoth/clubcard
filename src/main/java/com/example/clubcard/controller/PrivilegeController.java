package com.example.clubcard.controller;

import com.example.clubcard.controller.api.PrivilegeApi;
import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/privilege")
@RequiredArgsConstructor
public class PrivilegeController implements PrivilegeApi {
    private final PrivilegeService privilegeService;

    @GetMapping("/all")
    public ResponseEntity<List<PrivilegeResponse>> getAllPrivileges(){
        return ResponseEntity.ok(privilegeService.getAllPrivileges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivilegeResponse> getPrivilege(@PathVariable Long id){
        return ResponseEntity.ok(privilegeService.getPrivilege(id));
    }
}
