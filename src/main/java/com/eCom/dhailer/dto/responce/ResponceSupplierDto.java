package com.eCom.dhailer.dto.responce;

import com.eCom.dhailer.entity.Product;
import com.eCom.dhailer.entity.ProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponceSupplierDto {

    private String propertyId;
    private String Code;
    private String SupplierName;
    private String contactNumber;
    private String email;
    private List<ResponceProductCategoryDto>  productCategories;

}
