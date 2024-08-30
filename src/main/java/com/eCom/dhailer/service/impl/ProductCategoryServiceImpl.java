package com.eCom.dhailer.service.impl;

import com.eCom.dhailer.dto.request.RequestProductCategoryDto;
import com.eCom.dhailer.entity.Product;
import com.eCom.dhailer.entity.ProductCategory;
import com.eCom.dhailer.exception.DuplicateEntryException;
import com.eCom.dhailer.repo.ProductCategoryRepo;
import com.eCom.dhailer.repo.ProductRepo;
import com.eCom.dhailer.service.ProductCategoryService;
import com.eCom.dhailer.util.RandomKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepo productCategoryRepo;
    private final RandomKeyGenerator randomKeyGenerator;

    @Override
    public void create(RequestProductCategoryDto dto) {

        if(productCategoryRepo.existsByCategoryName((dto.getCategoryName())))
            throw new DuplicateEntryException("Category name already exists");

        productCategoryRepo.save(
                new ProductCategory().builder()
                        .propertyId(UUID.randomUUID().toString())
                        .Code(randomKeyGenerator.generateCategoryCode())
                        .categoryName(dto.getCategoryName())
                        .build()
        );
    }

    @Override
    public void update(String id, RequestProductCategoryDto dto) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Object findAll(Map<String, String> params) {
        return null;
    }
}
