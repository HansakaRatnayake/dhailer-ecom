package com.eCom.dhailer.api;

import com.eCom.dhailer.dto.request.RequestCustomerDto;
import com.eCom.dhailer.dto.request.RequestCustomerOrderDto;
import com.eCom.dhailer.service.impl.CustomerOrderServiceImpl;
import com.eCom.dhailer.util.StandardResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class CustomerOrderController {


    private final CustomerOrderServiceImpl customerOrderService;

    @PostMapping("/create")
    public ResponseEntity<StandardResponce> create(@RequestBody RequestCustomerOrderDto dto) {
        customerOrderService.create(dto);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "Customer Order successfully created", null),
                HttpStatus.CREATED
        );
    }


    @GetMapping("/list")
    public ResponseEntity<StandardResponce> findAll(@RequestParam String searchtext, @RequestParam Integer page, @RequestParam Integer size) {

        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200, "Customer Order List", customerOrderService.findAll(searchtext, page, size)),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<StandardResponce> getById(@PathVariable String id) {
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200, "Customer Order Data", customerOrderService.findById(id)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponce> delete(@PathVariable String id) {
        customerOrderService.delete(id);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(204,"Customer Order deleted",null),
                HttpStatus.CREATED
        );

    }

}
