package com.eCom.dhailer.api;

import com.eCom.dhailer.dto.request.RequestCustomerDto;
import com.eCom.dhailer.service.ProductImageService;
import com.eCom.dhailer.service.impl.CustomerServiceImpl;
import com.eCom.dhailer.util.StandardResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-image")
public class ProductImageController {


    private final ProductImageService productImageService;

    @PostMapping("/{product}")
    public ResponseEntity<StandardResponce> create(@RequestParam("productImage")MultipartFile file,@PathVariable String product) throws SQLException, IOException {
        productImageService.create(file,product);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "Product image successfully created", null),
                HttpStatus.CREATED
        );
    }


}
