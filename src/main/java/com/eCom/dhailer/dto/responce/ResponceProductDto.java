package com.eCom.dhailer.dto.responce;

import com.eCom.dhailer.entity.ProductImage;
import com.eCom.dhailer.entity.enums.PaymentMethod;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponceProductDto {

    private String propertyId;
    private Integer qty;
    private Double unitprice;
    private String description;
    private List<ResponceProductImageDto> productImages;
}
