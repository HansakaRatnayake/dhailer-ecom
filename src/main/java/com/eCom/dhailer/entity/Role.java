package com.eCom.dhailer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Role {

    @Id
    @Column(length = 80)
    private String roleId;

    @Column(length = 100)
    private String roleName;

    @Column(length = 100)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
