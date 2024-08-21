package com.example.clubcard.config.seed;

import com.example.clubcard.domain.enums.RoleEnum;
import com.example.clubcard.domain.model.Role;
import com.example.clubcard.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }

    private void loadRoles(){
        Arrays.stream(RoleEnum.values()).forEach(roleEnum -> {
            String name = roleEnum.name();
            if (!roleRepository.existsByName(name)){
                Role role = new Role();
                role.setName(name);
                roleRepository.save(role);
            }
        });
    }
}
