package ru.jigulin.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jigulin.weather.entity.Role;
import ru.jigulin.weather.repository.RoleRepository;

@Service
public class RoleService {

    private static final String DEFAULT_ROLE_NAME = "ROLE_USER";

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getDefaultRole() {
        return roleRepository.findByName(DEFAULT_ROLE_NAME).orElseThrow(() -> new RuntimeException("NO DEFAULT ROLE"));
    }
}
