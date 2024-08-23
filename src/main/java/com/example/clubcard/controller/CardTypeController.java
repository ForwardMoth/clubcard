package com.example.clubcard.controller;

import com.example.clubcard.controller.api.CardTypeApi;
import com.example.clubcard.domain.dto.request.card_type.CardTypeRequest;
import com.example.clubcard.domain.dto.response.card_type.CardTypeResponse;
import com.example.clubcard.service.CardTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card-type")
@RequiredArgsConstructor
public class CardTypeController implements CardTypeApi {
    private final CardTypeService cardTypeService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<CardTypeResponse>> getAllCardTypes() {
        return ResponseEntity.ok(cardTypeService.getAllCardTypes());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<CardTypeResponse> getCardType(@PathVariable Long id) {
        return ResponseEntity.ok(cardTypeService.getCardType(id));
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CardTypeResponse> createCardType(@RequestBody @Valid CardTypeRequest request) {
        return ResponseEntity.ok(cardTypeService.createCardType(request));
    }

    @PatchMapping("/{id}/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CardTypeResponse> updateCardType(@PathVariable Long id,
                                                           @RequestBody @Valid CardTypeRequest request) {
        return ResponseEntity.ok(cardTypeService.updateCardType(id, request));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCardType(@PathVariable Long id) {
        cardTypeService.deleteCardType(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
