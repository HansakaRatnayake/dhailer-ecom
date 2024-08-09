package com.eCom.dhailer.dto.responce;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponceCustomerDto {

    private String propertyId;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private Boolean isActive;

}
