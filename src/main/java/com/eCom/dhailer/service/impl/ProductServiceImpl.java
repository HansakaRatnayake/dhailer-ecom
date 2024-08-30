package com.eCom.dhailer.service.impl;

import com.eCom.dhailer.dto.request.RequestProductDto;
import com.eCom.dhailer.dto.responce.ResponceProductDto;
import com.eCom.dhailer.dto.responce.ResponceProductImageDto;
import com.eCom.dhailer.dto.responce.paginate.ProductPaginateDto;
import com.eCom.dhailer.entity.Product;
import com.eCom.dhailer.entity.ProductImage;
import com.eCom.dhailer.exception.EntryNotFoundException;
import com.eCom.dhailer.repo.ProductCategoryRepo;
import com.eCom.dhailer.repo.ProductImageRepo;
import com.eCom.dhailer.repo.ProductRepo;
import com.eCom.dhailer.repo.SupplierRepo;
import com.eCom.dhailer.service.ProductService;
import com.eCom.dhailer.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    public final ProductRepo productRepo;
    public final ProductImageRepo productImageRepo;
    public final FileDataExtractor fileDataExtractor;
    public final SupplierRepo supplierRepo;
    public final ProductCategoryRepo productCategoryRepo;


    @Override
    public void create(RequestProductDto dto) {




        Product product = Product.builder()
                .propertyId(UUID.randomUUID().toString())
                .qty(dto.getQty())
                .description(dto.getDescription())
                .unitPrice(dto.getUnitPrice())
                .salePrice(dto.getSalePrice())
                .discountPrice(dto.getDiscountPrice())
                .sales(dto.getSales())
                .productCategory(productCategoryRepo.findById(dto.getProductCategory()).orElseThrow(() -> new EntryNotFoundException("Category not found")))
                .supplier(supplierRepo.findById(dto.getSupplier()).orElseThrow(() -> new EntryNotFoundException("Supplier not found")))
                .build();

        productRepo.save(product);
    }

    @Override
    public ResponceProductDto findById(String id) {
        Optional<Product> selectedproduct = productRepo.findById(id);
        if (selectedproduct.isEmpty()) throw new EntryNotFoundException("Product Not Found");

        return createResponceProductDto(selectedproduct.get());
    }

    @Override
    public void update(String id, RequestProductDto dto) {
        Optional<Product> selectedproduct = productRepo.findById(id);
        if (selectedproduct.isEmpty()) throw new EntryNotFoundException("Product Not Found");

        Product product = Product.builder()
                .propertyId(id)
                .qty(dto.getQty())
                .description(dto.getDescription())
                .unitPrice(dto.getUnitPrice())
                .build();

        productRepo.save(product);

    }

    @Override
    public ProductPaginateDto findAll(String searchtext, Integer page, Integer size) {
        Page<Product> products = productRepo.findAllBySearchText(searchtext, PageRequest.of(page,size));
        return ProductPaginateDto.builder()
                .datalist(products.stream().map(this::createResponceProductDto).toList())
                .count(productRepo.countAllBySearchText(searchtext))
                .build();
    }

    @Override
    public void delete(String id) {

    }

    public ResponceProductDto createResponceProductDto(Product product){

        List<ResponceProductImageDto> responceProductImageDtoList = product.getProductImages().stream().map(this::createResponceProductImageDto).toList();

        return  ResponceProductDto.builder()
                .propertyId(product.getPropertyId())
                .description(product.getDescription())
                .qty(product.getQty())
                .unitPrice(product.getUnitPrice())
                .productImages(responceProductImageDtoList)
                .build();
    }

    public ResponceProductImageDto createResponceProductImageDto(ProductImage productImage){
        return  ResponceProductImageDto.builder()
                .PropertyId(productImage.getPropertyId())
//                .hash(fileDataExtractor.byteArrayToString(productImage.getHash()))
//                .directory(fileDataExtractor.byteArrayToString(productImage.getDirectory()))
//                .filename(fileDataExtractor.byteArrayToString(productImage.getFilename()))
                .resourceurl(fileDataExtractor.byteArrayToString(productImage.getResourceurl()))
                .build();
    }



}
