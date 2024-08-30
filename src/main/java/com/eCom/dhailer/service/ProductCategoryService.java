package com.eCom.dhailer.service;

import com.eCom.dhailer.dto.request.RequestProductCategoryDto;

import java.util.Map;

public interface ProductCategoryService {
    void create(RequestProductCategoryDto dto);

    void update(String id, RequestProductCategoryDto dto);


    void delete(String id);

    Object findAll(Map<String, String> params);
}
