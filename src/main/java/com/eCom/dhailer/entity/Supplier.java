package com.eCom.dhailer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @Column(length = 80)
    private String propertyId;

    @Column(length = 20,unique = true,nullable = false)
    private String Code;

    @Column(length = 50,unique = true,nullable = false)
    private String supplierName;

    @Column(length = 10,unique = true,nullable = false)
    private String contactNumber;

    @Column(length = 50,unique = true,nullable = false)
    private String email;


    @OneToMany(mappedBy = "supplier")
    private List<Product> products;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "supplycategory",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<ProductCategory>  productCategories;

}
