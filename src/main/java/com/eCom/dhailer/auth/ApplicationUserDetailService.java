package com.eCom.dhailer.auth;

import com.eCom.dhailer.entity.Role;
import com.eCom.dhailer.entity.User;
import com.eCom.dhailer.exception.EntryNotFoundException;
import com.eCom.dhailer.repo.UserRepo;
import com.eCom.dhailer.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ApplicationUserDetailService implements UserDetailsService {


    private final UserRepo userRepo;

    @Autowired
    public ApplicationUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> selectedUser = userRepo.findByEmail(username);

        if (selectedUser.isEmpty()) throw new EntryNotFoundException(String.format("User with email %s not found", username));

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();

        for (Role role:selectedUser.get().getRoles()){

            if (role.getRoleName().equals("USER")) simpleGrantedAuthorities.addAll(ApplicationUserRole.USER.grantedAuthorities());
            if (role.getRoleName().equals("ADMIN")) simpleGrantedAuthorities.addAll(ApplicationUserRole.ADMIN.grantedAuthorities());
            if (role.getRoleName().equals("CUSTOMER")) simpleGrantedAuthorities.addAll(ApplicationUserRole.CUSTOMER.grantedAuthorities());
            if (role.getRoleName().equals("MANAGER")) simpleGrantedAuthorities.addAll(ApplicationUserRole.MANAGER.grantedAuthorities());
            if (role.getRoleName().equals("CASHIER")) simpleGrantedAuthorities.addAll(ApplicationUserRole.CASHIER.grantedAuthorities());
        }


        return ApplicationUserDetail.builder()
                .authorities(simpleGrantedAuthorities)
                .username(username)
                .password(selectedUser.get().getPassword())
                .isCredentialsNonExpired(selectedUser.get().isCredentialsNonExpired())
                .isAccountNonExpired(selectedUser.get().isAccountNonExpired())
                .isEnabled(selectedUser.get().isEnabled())
                .isAccountNonLocked(selectedUser.get().isAccountNonLocked())
                .build();
    }
}
