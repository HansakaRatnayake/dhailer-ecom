package com.eCom.dhailer.dto.responce;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponceProductCategoryDto {

    private String propertyId;
    private String Code;
    private String categoryName;
}
