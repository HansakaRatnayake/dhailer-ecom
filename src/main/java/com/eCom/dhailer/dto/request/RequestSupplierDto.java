package com.eCom.dhailer.dto.request;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestSupplierDto {


    private String supplierName;
    private String contactNumber;
    private String email;
    private List<String> productCategories;

}
