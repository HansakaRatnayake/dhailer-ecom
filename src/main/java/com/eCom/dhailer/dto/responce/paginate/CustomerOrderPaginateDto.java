package com.eCom.dhailer.dto.responce.paginate;

import com.eCom.dhailer.dto.responce.ResponceCustomerDto;
import com.eCom.dhailer.dto.responce.ResponceCutomerOrderDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderPaginateDto {

    private List<ResponceCutomerOrderDto> datalist;
    private Integer count;
}
