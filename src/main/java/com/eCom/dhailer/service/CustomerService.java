package com.eCom.dhailer.service;

import com.eCom.dhailer.dto.request.RequestCustomerDto;
import com.eCom.dhailer.dto.responce.ResponceCustomerDto;
import com.eCom.dhailer.dto.responce.paginate.CustomerPaginateDto;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    public void create(RequestCustomerDto dto);
    public ResponceCustomerDto findById(String id);

    public void update(String id,RequestCustomerDto dto);

    public CustomerPaginateDto findAll(String searchtext, Integer page, Integer size);

    public void delete(String id);


}
