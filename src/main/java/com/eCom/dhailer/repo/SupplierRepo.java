package com.eCom.dhailer.repo;



import com.eCom.dhailer.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SupplierRepo extends JpaRepository<Supplier,String> {

    @Query(value = "SELECT pc.Code FROM ProductCategory pc")
    List<String> findAllByCategoryCode();

    boolean existsBySupplierName(String supplierName);
    boolean existsByEmail(String email);
    boolean existsByContactNumber(String phone);
    
}
