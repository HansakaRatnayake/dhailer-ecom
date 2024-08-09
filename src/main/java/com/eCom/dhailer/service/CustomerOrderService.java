package com.eCom.dhailer.service;

import com.eCom.dhailer.dto.request.RequestCustomerOrderDto;
import com.eCom.dhailer.dto.responce.ResponceCutomerOrderDto;
import com.eCom.dhailer.dto.responce.paginate.CustomerOrderPaginateDto;

public interface CustomerOrderService {
    public void create(RequestCustomerOrderDto dto);
    public ResponceCutomerOrderDto findById(String id);

    public void update(String id, RequestCustomerOrderDto dto);

    public CustomerOrderPaginateDto findAll(String customerId, Integer page, Integer size);

    public void delete(String id);

}
