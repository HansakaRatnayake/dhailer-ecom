package com.eCom.dhailer.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @Column(length = 80)
    private String userId;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 80, nullable = false)
    private String displayName;

    @Column(length = 250, nullable = false)
    private String password;

    @Column(columnDefinition = "TINYINT")
    private boolean isAccountNonExpired;

    @Column(columnDefinition = "TINYINT")
    private boolean isAccountNonLocked;

    @Column(columnDefinition = "TINYINT")
    private boolean isCredentialsNonExpired;

    @Column(columnDefinition = "TINYINT")
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
