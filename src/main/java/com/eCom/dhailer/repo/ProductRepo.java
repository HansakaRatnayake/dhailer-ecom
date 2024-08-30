package com.eCom.dhailer.repo;

import com.eCom.dhailer.entity.Customer;
import com.eCom.dhailer.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {

    @Query(value = "SELECT * FROM product WHERE description LIKE  %?1% ORDER BY description DESC", nativeQuery = true)
    public Page<Product> findAllBySearchText(String searchtext, Pageable pageable);


    @Query(value = "SELECT COUNT(*) from product WHERE description LIKE  %?1%", nativeQuery = true)
    public  Integer countAllBySearchText(String searchtext);

}


//++++JPQL+++++++++++++++++++

//    @Query("SELECT p FROM Product p WHERE p.description LIKE %:searchText% ORDER BY p.description DESC")
//    public List<Product> findAllBySearchText(@Param("searchText") String searchText, Pageable pageable);

//    @Query(value = "SELECT COUNT(p) FROM Product p where p.description LIKE %:searchtext%")
//    public Integer countAllBySearchText(@Param("searchtext") String searchtext);

// ++++JPQL++++++++++++++++++++
