package com.eCom.dhailer.service.impl;

import com.eCom.dhailer.dto.request.RequestCustomerOrderDto;
import com.eCom.dhailer.dto.responce.ResponceCutomerOrderDto;
import com.eCom.dhailer.dto.responce.paginate.CustomerOrderPaginateDto;
import com.eCom.dhailer.entity.Customer;
import com.eCom.dhailer.entity.CustomerOrder;
import com.eCom.dhailer.entity.Product;
import com.eCom.dhailer.exception.EntryNotFoundException;
import com.eCom.dhailer.repo.CustomerOrderRepo;
import com.eCom.dhailer.repo.CustomerRepo;
import com.eCom.dhailer.repo.ProductRepo;
import com.eCom.dhailer.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepo customerOrderRepo;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;

    @Override
    public void create(RequestCustomerOrderDto dto) {

        Optional<Customer> selectedcustomer = customerRepo.findById(dto.getCustomer());
        if (selectedcustomer.isEmpty()) throw new EntryNotFoundException("Given Customer Not Found");


        Optional<Product> selectedproduct = productRepo.findById(dto.getProduct());
        if (selectedproduct.isEmpty()) throw new EntryNotFoundException("Orderd Product Not Found");


        CustomerOrder customerOrder = CustomerOrder.builder()
                .propertyid(UUID.randomUUID().toString())
                .qty(dto.getQty())
                .total(dto.getTotal())
                .createddate(dto.getCreateddate())
                .paymentMethod(dto.getPaymentMethod())
                .product(selectedproduct.get())
                .customer(selectedcustomer.get())
                .build();
        customerOrderRepo.save(customerOrder);


    }

    @Override
    public ResponceCutomerOrderDto findById(String id) {
        Optional<CustomerOrder> selectedorder = customerOrderRepo.findById(id);
        if (selectedorder.isEmpty()) throw new EntryNotFoundException("Order not found");

        return createResponceCutomerOrderDto(selectedorder.get());
    }

    @Override
    public void update(String id, RequestCustomerOrderDto dto) {

        Optional<CustomerOrder> selectedorder = customerOrderRepo.findById(id);
        if (selectedorder.isEmpty()) throw new EntryNotFoundException("Order not found");

        Optional<Customer> selectedcustomer = customerRepo.findById(dto.getCustomer());
        if (selectedcustomer.isEmpty()) throw new EntryNotFoundException("Customer Not Found");


        Optional<Product> selectedproduct = productRepo.findById(dto.getProduct());
        if (selectedproduct.isEmpty()) throw new EntryNotFoundException("Product Not Found");


        CustomerOrder customerOrder = CustomerOrder.builder()
                .propertyid(id)
                .qty(dto.getQty())
                .total(dto.getTotal())
                .createddate(dto.getCreateddate())
                .paymentMethod(dto.getPaymentMethod())
                .product(selectedproduct.get())
                .customer(selectedcustomer.get())
                .build();
        customerOrderRepo.save(customerOrder);


    }

    @Override
    public CustomerOrderPaginateDto findAll(String customerId, Integer page, Integer size) {
        Page<CustomerOrder> customerOrders = customerOrderRepo.findAllBySearchText(customerId, PageRequest.of(page, size));

        return CustomerOrderPaginateDto.builder()
                .datalist(customerOrders.stream().map(this::createResponceCutomerOrderDto).toList())
                .count(customerOrderRepo.countAllBySearchText(customerId))
                .build();
    }

    @Override
    public void delete(String id) {
        if (!customerOrderRepo.existsById(id)) throw new EntryNotFoundException("Order not exist");
        customerOrderRepo.deleteById(id);
    }


    public ResponceCutomerOrderDto createResponceCutomerOrderDto(CustomerOrder customerOrder){
        return ResponceCutomerOrderDto.builder()
                .propertyid(customerOrder.getPropertyid())
                .createddate(customerOrder.getCreateddate())
                .qty(customerOrder.getQty())
                .total(customerOrder.getTotal())
                .product(customerOrder.getProduct().getDescription())
                .customer(customerOrder.getCustomer().getName())
                .paymentMethod(customerOrder.getPaymentMethod())
                .build();
    }
}
