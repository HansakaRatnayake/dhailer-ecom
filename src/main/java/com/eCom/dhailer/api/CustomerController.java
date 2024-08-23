package com.eCom.dhailer.api;

import com.eCom.dhailer.dto.request.RequestCustomerDto;
import com.eCom.dhailer.service.impl.CustomerServiceImpl;
import com.eCom.dhailer.util.StandardResponce;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Star;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {


    private final CustomerServiceImpl customerService;

    @PostMapping("/create")
    public ResponseEntity<StandardResponce> create(@RequestBody RequestCustomerDto dto) {
        customerService.create(dto);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "Customer successfully created", null),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponce> update(@PathVariable String id, @RequestBody RequestCustomerDto dto) {
        customerService.update(id, dto);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "Customer Updated", null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponce> findAll(@RequestParam String searchtext, @RequestParam Integer page, @RequestParam Integer size) {

        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200, "Customer List", customerService.findAll(searchtext, page, size)),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<StandardResponce> getById(@PathVariable String id) {
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200, "Customer Data", customerService.findById(id)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponce> delete(@PathVariable String id) {
        customerService.delete(id);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(204,"Customer deleted",null),
                HttpStatus.CREATED
        );

    }

}
