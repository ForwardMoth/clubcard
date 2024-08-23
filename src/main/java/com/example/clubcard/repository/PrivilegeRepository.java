package com.example.clubcard.repository;

import com.example.clubcard.domain.entity.Privilege;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Optional<Privilege> findByName(String name);

    Boolean existsByName(String name);
}
