package com.eCom.dhailer.repo;



import com.eCom.dhailer.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductCategoryRepo extends JpaRepository<ProductCategory,String> {

    boolean existsByCategoryName(String name);

    @Query(value = "SELECT pc.Code FROM ProductCategory pc")
    public List<String> findAllByCategoryCode();


}
