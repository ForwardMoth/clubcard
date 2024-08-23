package com.example.clubcard.service;

import com.example.clubcard.domain.entity.Privilege;

public interface PrivilegeService {
    Privilege getByName(String name);
}
