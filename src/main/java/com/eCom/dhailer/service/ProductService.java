package com.eCom.dhailer.service;

import com.eCom.dhailer.dto.request.RequestProductDto;
import com.eCom.dhailer.dto.responce.ResponceProductDto;
import com.eCom.dhailer.dto.responce.paginate.ProductPaginateDto;

public interface ProductService {

    public void create(RequestProductDto dto);
    public ResponceProductDto findById(String id);

    public void update(String id,RequestProductDto dto);

    public ProductPaginateDto findAll(String searchtext, Integer page, Integer size);

    public void delete(String id);

}
