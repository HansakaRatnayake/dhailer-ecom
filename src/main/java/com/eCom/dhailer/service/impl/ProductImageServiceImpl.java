package com.eCom.dhailer.service.impl;

import com.amazonaws.services.mq.model.InternalServerErrorException;
import com.eCom.dhailer.dto.responce.ResponceProductImageDto;
import com.eCom.dhailer.entity.Product;
import com.eCom.dhailer.entity.ProductImage;
import com.eCom.dhailer.exception.EntryNotFoundException;
import com.eCom.dhailer.repo.ProductImageRepo;
import com.eCom.dhailer.repo.ProductRepo;
import com.eCom.dhailer.service.FileService;
import com.eCom.dhailer.service.ProductImageService;
import com.eCom.dhailer.util.CommonFileSavedBinaryDataDto;
import com.eCom.dhailer.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    @Value("${aws.bucket_name}")
    private String bucketname;

    private final ProductImageRepo productImageRepo;

    private final ProductRepo productRepo;

    private final FileService fileService;

    private final FileDataExtractor dataExtractor;


    @Override
    public void create(MultipartFile file, String productId) throws SQLException, IOException {

        CommonFileSavedBinaryDataDto resource = null;

        Optional<Product> selectedproduct = productRepo.findById(productId);

        if(selectedproduct.isEmpty()){
            throw new EntryNotFoundException("The Product Not Found");
        }

        try {
            resource = fileService.createResource(file, "dhailer/product_images/", bucketname);

            ProductImage productImage = ProductImage.builder()
                    .PropertyId(UUID.randomUUID().toString())
                    .hash(dataExtractor.blobToByteArray(resource.getHash()))
                    .directory(resource.getDirectory().getBytes())
                    .filename(dataExtractor.blobToByteArray(resource.getFilename()))
                    .resourceurl(dataExtractor.blobToByteArray(resource.getResourceurl()))
                    .product(selectedproduct.get())
                    .build();

            productImageRepo.save(productImage);

        }catch (Exception e){
            fileService.deleteResource(bucketname, resource.getDirectory(),dataExtractor.actualFileName(new InputStreamReader(resource.getFilename().getBinaryStream())));
            throw new InternalServerErrorException("Somthing went wrong :( "+e.getMessage());
        }

    }

    @Override
    public ResponceProductImageDto findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
