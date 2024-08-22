package com.example.clubcard.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PrivilegeEnum {
    STANDARD("Стандартный"),
    EXTRA("Повышенный"),
    VIP("VIP");

    private final String name;
}
