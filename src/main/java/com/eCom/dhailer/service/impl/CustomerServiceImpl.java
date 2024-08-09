package com.eCom.dhailer.service.impl;

import com.eCom.dhailer.dto.request.RequestCustomerDto;
import com.eCom.dhailer.dto.responce.ResponceCustomerDto;
import com.eCom.dhailer.dto.responce.paginate.CustomerPaginateDto;
import com.eCom.dhailer.entity.Customer;
import com.eCom.dhailer.exception.EntryNotFoundException;
import com.eCom.dhailer.repo.CustomerRepo;
import com.eCom.dhailer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public void create(RequestCustomerDto dto) {


        Customer customer = Customer.builder()
                .propertyId(UUID.randomUUID().toString())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getMobile())
                .adddress(dto.getAddress())
                .isActive(dto.getIsActive())
                .build();


        customerRepo.save(customer);
        System.out.println(customer.getAdddress());
    }

    @Override
    public ResponceCustomerDto findById(String id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isEmpty()) throw new EntryNotFoundException("Customer not found");
        return toResponceCustomerDto(customer.get());
    }

    @Override
    public void update(String id, RequestCustomerDto dto) {
        Optional<Customer> cus = customerRepo.findById(id);
        if (cus.isEmpty()) throw new EntryNotFoundException("Customer not found");

        Customer customer = cus.get();

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getMobile());
        customer.setAdddress(dto.getAddress());
        customer.setIsActive(dto.getIsActive());

        customerRepo.save(customer);
    }

    @Override
    public CustomerPaginateDto findAll(String searchtext, Integer page, Integer size) {
        Page<Customer> allBySearchText = customerRepo.findAllBySearchText(searchtext, PageRequest.of(page, size));
        System.out.println(allBySearchText);
        return new CustomerPaginateDto(
                allBySearchText.stream().map(this::toResponceCustomerDto).toList(),
                customerRepo.countAllBySearchText(searchtext)
        );

    }

    @Override
    public void delete(String id) {
        if(!customerRepo.existsById(id)) throw new RuntimeException("Cutomer Not exists");
        customerRepo.deleteById(id);
    }

    private ResponceCustomerDto toResponceCustomerDto(Customer customer){
        return ResponceCustomerDto.builder()
                .propertyId(customer.getPropertyId())
                .name(customer.getName())
                .mobile(customer.getPhone())
                .email(customer.getEmail())
                .address(customer.getAdddress())
                .isActive(customer.getIsActive())
                .build();
    }

}
