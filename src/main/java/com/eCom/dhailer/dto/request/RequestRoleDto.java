package com.eCom.dhailer.dto.request;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestRoleDto {

    private String roleName;
    private String description;

}