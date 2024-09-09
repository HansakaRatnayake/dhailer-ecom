package com.eCom.dhailer.repo;

import com.eCom.dhailer.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepo extends JpaRepository<ProductImage, String> {


}
