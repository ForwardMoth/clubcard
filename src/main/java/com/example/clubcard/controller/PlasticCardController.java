package com.example.clubcard.controller;

import com.example.clubcard.controller.api.PlasticCardApi;
import com.example.clubcard.domain.dto.request.plastic_card.PlasticCardRequest;
import com.example.clubcard.domain.dto.response.plastic_card.PlasticCardResponse;
import com.example.clubcard.service.PlasticCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plastic-card")
@RequiredArgsConstructor
public class PlasticCardController implements PlasticCardApi {
    private final PlasticCardService plasticCardService;

    @Override
    @PostMapping("/{id}/new")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<PlasticCardResponse> createCard(@PathVariable Long id,
                                                          @RequestBody PlasticCardRequest request) {
        return ResponseEntity.ok(plasticCardService.createCard(id, request));
    }

    @Override
    @PatchMapping("/{id}/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<PlasticCardResponse> updateCard(@PathVariable Long id,
                                                          @RequestBody PlasticCardRequest request) {
        return ResponseEntity.ok(plasticCardService.updateCard(id, request));
    }

    @Override
    @PatchMapping("/{id}/update/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PlasticCardResponse> updateStatusCard(Long id) {
        return ResponseEntity.ok(plasticCardService.updateStatusCard(id));
    }

    @Override
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteCard(Long id) {
        plasticCardService.deleteCard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
