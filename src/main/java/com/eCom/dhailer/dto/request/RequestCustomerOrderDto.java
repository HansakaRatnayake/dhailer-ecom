package com.eCom.dhailer.dto.request;

import com.eCom.dhailer.entity.enums.PaymentMethod;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCustomerOrderDto {

    private Date createddate;
    private Integer qty;
    private Double total;
    private PaymentMethod paymentMethod;
    private String customer;
    private String product;
}
