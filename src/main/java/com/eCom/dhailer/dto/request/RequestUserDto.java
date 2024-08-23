package com.eCom.dhailer.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestUserDto {
    private String email;
    private String displayName;
    private String password;
    private String roleType;
}
