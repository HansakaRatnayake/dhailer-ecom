package com.eCom.dhailer.dto.responce.paginate;


import com.eCom.dhailer.dto.responce.ResponceCustomerDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPaginateDto {

    private List<ResponceCustomerDto> datalist;
    private Integer count;
}
