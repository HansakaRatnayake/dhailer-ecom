package com.eCom.dhailer.service.impl;

import com.eCom.dhailer.dto.request.RequestSupplierDto;
import com.eCom.dhailer.dto.responce.ResponceSupplierDto;
import com.eCom.dhailer.entity.ProductCategory;
import com.eCom.dhailer.entity.Supplier;
import com.eCom.dhailer.exception.DuplicateEntryException;
import com.eCom.dhailer.repo.ProductCategoryRepo;
import com.eCom.dhailer.repo.SupplierRepo;
import com.eCom.dhailer.service.SupplierService;
import com.eCom.dhailer.util.RandomKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepo supplierRepo;
    private final ProductCategoryRepo productCategoryRepo;
    private final RandomKeyGenerator randomKeyGenerator;

    @Override
    public List<ResponceSupplierDto> getAll() {
        return List.of();
    }

    @Override
    public void create(RequestSupplierDto dto) {


        if (supplierRepo.existsBySupplierName(dto.getSupplierName()))  throw new DuplicateEntryException("Supplier name already exists");
        if (supplierRepo.existsByEmail(dto.getEmail()))  throw new DuplicateEntryException("Email already exists");
        if(supplierRepo.existsByContactNumber(dto.getContactNumber()))  throw new DuplicateEntryException("Contact number already exists");


        List<ProductCategory> allCategory = productCategoryRepo.findAll();
        List<String> productCategories = dto.getProductCategories();

        List<ProductCategory> selectedProductCategory = new ArrayList<>();

        for (String productCategory : productCategories){
            if (allCategory.stream().anyMatch(c->c.getPropertyId().equals(productCategory))) {
                selectedProductCategory.add(allCategory.stream().filter(category -> category.getPropertyId().equals(productCategory)).findFirst().get());
            }
        }

        supplierRepo.save(
                Supplier.builder()
                        .propertyId(UUID.randomUUID().toString())
                        .Code(randomKeyGenerator.generateSupplierCode())
                        .email(dto.getEmail())
                        .supplierName(dto.getSupplierName())
                        .contactNumber(dto.getContactNumber())
                        .productCategories(selectedProductCategory)
                        .build()
        );

    }
}
