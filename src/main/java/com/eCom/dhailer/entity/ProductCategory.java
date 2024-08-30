package com.eCom.dhailer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @Column(length = 80)
    private String propertyId;

    @Column(length = 20,unique = true,nullable = false)
    private String Code;

    @Column(length = 50,unique = true,nullable = false)
    private String categoryName;

    @ManyToMany(mappedBy = "productCategories")
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;


}
