package com.example.clubcard.repository;

import com.example.clubcard.domain.entity.Privilege;
import com.example.clubcard.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Boolean existsByPrivilege(Privilege privilege);
}
