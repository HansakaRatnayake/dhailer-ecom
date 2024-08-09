package com.eCom.dhailer.entity;

import com.eCom.dhailer.entity.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {

    @Id
    private String propertyid;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private Date createddate;

    private Integer qty;

    private Double total;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "propertyId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "propertyId")
    private Product product;


}
