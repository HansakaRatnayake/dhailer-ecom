package com.eCom.dhailer.api;

import com.eCom.dhailer.dto.request.RequestCustomerDto;
import com.eCom.dhailer.dto.request.RequestProductDto;
import com.eCom.dhailer.service.impl.CustomerServiceImpl;
import com.eCom.dhailer.service.impl.ProductServiceImpl;
import com.eCom.dhailer.util.StandardResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<StandardResponce> create(@RequestBody RequestProductDto dto) {
        productService.create(dto);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "Product successfully created", null),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponce> update(@PathVariable String id, @RequestBody RequestProductDto dto) {
        productService.update(id, dto);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "Product Updated", null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponce> findAll(@RequestParam String searchtext, @RequestParam Integer page, @RequestParam Integer size) {

        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200, "Product List", productService.findAll(searchtext, page, size)),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<StandardResponce> getById(@PathVariable String id) {
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200, "Product Data", productService.findById(id)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponce> delete(@PathVariable String id) {
        productService.delete(id);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(204,"Product deleted",null),
                HttpStatus.CREATED
        );

    }

}
