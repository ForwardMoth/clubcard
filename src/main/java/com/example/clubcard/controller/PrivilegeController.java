package com.example.clubcard.controller;

import com.example.clubcard.controller.api.PrivilegeApi;
import com.example.clubcard.domain.dto.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.privilege.PrivilegeResponse;
import com.example.clubcard.service.PrivilegeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/privilege")
@RequiredArgsConstructor
public class PrivilegeController implements PrivilegeApi {
    private final PrivilegeService privilegeService;

    @GetMapping("/all")
    public ResponseEntity<List<PrivilegeResponse>> getAllPrivileges() {
        return ResponseEntity.ok(privilegeService.getAllPrivileges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivilegeResponse> getPrivilege(@PathVariable Long id) {
        return ResponseEntity.ok(privilegeService.getPrivilege(id));
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PrivilegeResponse> createPrivilege(@RequestBody @Valid PrivilegeRequest request) {
        return ResponseEntity.ok(privilegeService.createPrivilege(request));
    }

    @PatchMapping("/{id}/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PrivilegeResponse> updatePrivilege(@PathVariable Long id,
                                                             @RequestBody @Valid PrivilegeRequest request) {
        return ResponseEntity.ok(privilegeService.updatePrivilege(id, request));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletePrivilege(@PathVariable Long id) {
        privilegeService.deletePrivilege(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
