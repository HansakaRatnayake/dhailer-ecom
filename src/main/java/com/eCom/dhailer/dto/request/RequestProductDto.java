package com.eCom.dhailer.dto.request;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {

    private Integer qty;
    private Double unitprice;
    private String description;
}
