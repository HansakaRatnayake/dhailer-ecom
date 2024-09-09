package com.eCom.dhailer.dto.responce;

import com.eCom.dhailer.entity.ProductImage;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponceProductImageDto {

    private String propertyId;

    private String resourceUrl;

    private String image;
}
