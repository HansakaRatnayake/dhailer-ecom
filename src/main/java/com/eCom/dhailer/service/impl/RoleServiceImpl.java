package com.eCom.dhailer.service.impl;

import com.eCom.dhailer.dto.request.RequestRoleDto;
import com.eCom.dhailer.entity.Role;
import com.eCom.dhailer.repo.RoleRepo;
import com.eCom.dhailer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void create(RequestRoleDto dto) {
       roleRepo.save(
               Role.builder()
                       .roleId(UUID.randomUUID().toString())
                       .roleName(dto.getRoleName())
                       .description(dto.getDescription())
                       .build()
       );

    }

    @Override
    public void initializeUserRoles() {
        if (roleRepo.findAll().isEmpty()) {

            roleRepo.saveAll(List.of(
                    Role.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("ADMIN")
                            .description("Admin description")
                            .build(),
                    Role.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("USER")
                            .description("Admin description")
                            .build(),
                    Role.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("CUSTOMER")
                            .description("Admin description")
                            .build(),
                    Role.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("MANAGER")
                            .description("Admin description")
                            .build(),
                    Role.builder()
                            .roleId(UUID.randomUUID().toString())
                            .roleName("CASHIER")
                            .description("Admin description")
                            .build()
            ));
        }
    }
}
