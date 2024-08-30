package com.eCom.dhailer.api;

import com.eCom.dhailer.dto.request.RequestProductCategoryDto;
import com.eCom.dhailer.service.ProductCategoryService;
import com.eCom.dhailer.util.StandardResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class ProductCategoryController {


    private final ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<StandardResponce> create(@RequestBody RequestProductCategoryDto dto) {
        productCategoryService.create(dto);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "Category successfully created", null),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponce> update(@PathVariable String id,@RequestBody RequestProductCategoryDto dto) {
        productCategoryService.update(id, dto);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "Category Updated", null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponce> findAll(@RequestParam Map<String,String> params) {

        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200, "Category List", productCategoryService.findAll(params)),
                HttpStatus.OK
        );
    }

//    @GetMapping("{id}")
//    public ResponseEntity<StandardResponce> getById(@PathVariable String id) {
//        return new ResponseEntity<StandardResponce>(
//                new StandardResponce(200, "Category Data", productCategoryService.findById(id)),
//                HttpStatus.OK
//        );
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponce> delete(@PathVariable String id) {
        productCategoryService.delete(id);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(204,"Category deleted",null),
                HttpStatus.CREATED
        );

    }

}
