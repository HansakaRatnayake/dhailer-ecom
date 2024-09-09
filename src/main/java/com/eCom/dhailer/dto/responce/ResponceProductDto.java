package com.eCom.dhailer.dto.responce;

import com.eCom.dhailer.entity.ProductCategory;
import com.eCom.dhailer.entity.Supplier;
import lombok.*;


import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponceProductDto {

    private String propertyId;
    private String code;
    private Integer qty;
    private BigDecimal unitPrice;
    private String description;
    private Integer sales;
    private BigDecimal salePrice;
    private BigDecimal discountPrice;
    private BigDecimal revenue;
    private String productCategory;
    private String supplier;
    private List<ResponceProductImageDto> productImages;

}
