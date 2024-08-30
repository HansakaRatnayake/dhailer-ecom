package com.eCom.dhailer.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductImageDto {

    private String PropertyId;

    private String directory;

    private String resourceurl;

    private String hash;

    private String filename;
}
