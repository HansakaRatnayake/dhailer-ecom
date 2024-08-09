package com.eCom.dhailer.repo;

import com.eCom.dhailer.entity.CustomerOrder;
import com.eCom.dhailer.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, String> {

    @Query(value = "select co FROM CustomerOrder co WHERE co.customer.propertyId LIKE %:customerid% ORDER BY co.customer.propertyId")
    public Page<CustomerOrder> findAllBySearchText(@Param("customerid") String customerid, Pageable pageable);

    @Query(value = "select COUNT(co) FROM CustomerOrder co WHERE co.customer.propertyId LIKE %:customerid%")
    public Integer countAllBySearchText(@Param("customerid") String customerid);
}
