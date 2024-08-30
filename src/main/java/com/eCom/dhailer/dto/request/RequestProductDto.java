package com.eCom.dhailer.dto.request;

import com.eCom.dhailer.dto.responce.ResponceProductCategoryDto;
import com.eCom.dhailer.dto.responce.ResponceProductImageDto;
import com.eCom.dhailer.dto.responce.ResponceSupplierDto;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {

    private Integer qty;
    private BigDecimal unitPrice;
    private String description;
    private Integer sales;
    private BigDecimal salePrice;
    private BigDecimal discountPrice;
    private String productCategory;
    private String supplier;
    private List<RequestProductImageDto> productImages;
}
