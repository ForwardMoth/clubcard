package com.example.clubcard.controller;

import com.example.clubcard.controller.api.PlasticCardApi;
import com.example.clubcard.domain.dto.page.PageDto;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardFilterRequest;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardRequest;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardResponse;
import com.example.clubcard.service.PlasticCardService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/plastic-card")
@RequiredArgsConstructor
public class PlasticCardController implements PlasticCardApi {
    private final PlasticCardService plasticCardService;

    @Override
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<PlasticCardResponse>> getAllPlasticCards(@RequestParam(defaultValue = "0")
                                                                        @Min(0)
                                                                        Integer offset,
                                                                        @RequestParam(defaultValue = "10")
                                                                        @Min(1)
                                                                        Integer limit,
                                                                        @RequestParam(defaultValue = "PROGRESS", required = false)
                                                                        String status,
                                                                        @RequestParam(required = false)
                                                                        Instant createdAt,
                                                                        @RequestParam(required = false)
                                                                        Long cardTypeId) {
        return ResponseEntity.ok(plasticCardService.getAllPlasticCards(
                new PageDto(offset, limit), new PlasticCardFilterRequest(status, createdAt, cardTypeId)
        ));
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PlasticCardResponse> getPlasticCard(@PathVariable Long id) {
        return ResponseEntity.ok(plasticCardService.getPlasticCard(id));
    }

    @Override
    @PostMapping("/{userId}/new")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<PlasticCardResponse> createCard(@PathVariable Long userId,
                                                          @RequestBody PlasticCardRequest request) {
        return ResponseEntity.ok(plasticCardService.createCard(userId, request));
    }

    @Override
    @PatchMapping("/{userId}/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<PlasticCardResponse> updateCard(@PathVariable Long userId,
                                                          @RequestBody PlasticCardRequest request) {
        return ResponseEntity.ok(plasticCardService.updateCard(userId, request));
    }

    @Override
    @PatchMapping("/{userId}/update/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PlasticCardResponse> updateStatusCard(Long userId) {
        return ResponseEntity.ok(plasticCardService.updateStatusCard(userId));
    }

    @Override
    @DeleteMapping("/{userId}/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteCard(Long userId) {
        plasticCardService.deleteCard(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
