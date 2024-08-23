package com.example.clubcard.controller.admin;

import com.example.clubcard.controller.admin.api.AdminApi;
import com.example.clubcard.domain.dto.request.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.service.PrivilegeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController implements AdminApi {
    private final PrivilegeService privilegeService;

    @PostMapping("/privilege/new")
    public ResponseEntity<PrivilegeResponse> createPrivilege(@RequestBody @Valid PrivilegeRequest request) {
        return ResponseEntity.ok(privilegeService.createPrivilege(request));
    }

    @PatchMapping("/privilege/{id}/update")
    public ResponseEntity<?> updatePrivilege(@PathVariable Long id, @RequestBody @Valid PrivilegeRequest request){
        return ResponseEntity.ok("1234");
    }

    @DeleteMapping("/privilege/{id}/delete")
    public ResponseEntity<?> deletePrivilege(@PathVariable Long id){
        return ResponseEntity.ok("1234");
    }


}
