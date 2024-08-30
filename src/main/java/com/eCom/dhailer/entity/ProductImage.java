package com.eCom.dhailer.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

    @Id
    private String PropertyId;

    @Lob
    private byte[] directory;
    @Lob
    private byte[] resourceurl;
    @Lob
    private byte[] hash;
    @Lob
    private byte[] filename;

    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "propertyId")
    private Product product;
}
