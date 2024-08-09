package com.eCom.dhailer.dto.responce.paginate;

import com.eCom.dhailer.dto.responce.ResponceProductDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPaginateDto {

    private List<ResponceProductDto> datalist;
    private Integer count;
}
