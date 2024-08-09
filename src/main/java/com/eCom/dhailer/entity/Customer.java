package com.eCom.dhailer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(length = 80, nullable = false)
    private String propertyId;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 100, nullable = false,unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 255, nullable = false)
    private String adddress;

    @Column(columnDefinition = "TINYINT")
    private Boolean isActive;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerOrder> customerOrders;
}
