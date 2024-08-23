package com.eCom.dhailer.service.impl;

import com.eCom.dhailer.dto.request.RequestUserDto;
import com.eCom.dhailer.entity.Role;
import com.eCom.dhailer.entity.User;
import com.eCom.dhailer.exception.DuplicateEntryException;
import com.eCom.dhailer.exception.EntryNotFoundException;
import com.eCom.dhailer.repo.RoleRepo;
import com.eCom.dhailer.repo.UserRepo;
import com.eCom.dhailer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(RequestUserDto dto) {
        Optional<User> selectedUser = userRepo.findByEmail(dto.getEmail());

        if (selectedUser.isPresent())
            throw new DuplicateEntryException("User already exists");

        Optional<Role> selectedRoleName = roleRepo.findByRoleName(dto.getRoleType());

        if (selectedRoleName.isEmpty())
            throw new EntryNotFoundException(String.format("Role %s not found", dto.getRoleType()));

        Set<Role> object = new HashSet<>();
        object.add(selectedRoleName.get());

        userRepo.save(
                User.builder()
                        .userId(UUID.randomUUID().toString())
                        .email(dto.getEmail())
                        .displayName(dto.getDisplayName())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .roles(object)
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .build()
        );
    }

    @Override
    public void initializeAdmin() {

        if (userRepo.findByEmail("admin123@gmail.com").isEmpty()){
            Optional<Role> selectedRoleName = roleRepo.findByRoleName("ADMIN");
            if (selectedRoleName.isEmpty())
                throw new EntryNotFoundException("Admin role not found");

            Set<Role> objects = new HashSet<>();
            objects.add(selectedRoleName.get());
            userRepo.save(
                    User.builder()
                            .userId(UUID.randomUUID().toString())
                            .email("admin123@gmail.com")
                            .displayName("Admin")
                            .password(passwordEncoder.encode("1234"))
                            .roles(objects)
                            .isAccountNonExpired(true)
                            .isAccountNonLocked(true)
                            .isCredentialsNonExpired(true)
                            .isEnabled(true)
                            .build()
            );
        }
    }
}
