package com.eCom.dhailer.repo;

import com.eCom.dhailer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, String> {

    @Query(value = "SELECT * FROM customer WHERE name LIKE %?1% OR adddress LIKE %?1% OR phone LIKE %?1%" , nativeQuery = true)
    public Page<Customer> findAllBySearchText(String searchtext, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM customer WHERE name LIKE %?1% OR adddress LIKE %?1% OR phone LIKE %?1%" , nativeQuery = true)
    public Integer countAllBySearchText(String searchtext);

}
