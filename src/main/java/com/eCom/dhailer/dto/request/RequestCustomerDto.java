package com.eCom.dhailer.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCustomerDto {

    private String name;
    private String email;
    private String mobile;
    private String address;
    private Boolean isActive;

}
