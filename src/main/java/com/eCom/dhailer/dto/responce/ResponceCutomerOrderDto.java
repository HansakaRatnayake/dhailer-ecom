package com.eCom.dhailer.dto.responce;

import com.eCom.dhailer.entity.Customer;
import com.eCom.dhailer.entity.Product;
import com.eCom.dhailer.entity.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponceCutomerOrderDto {

    private String propertyid;
    private Date createddate;
    private Integer qty;
    private Double total;
    private PaymentMethod paymentMethod;
    private String customer;
    private String product;
}
