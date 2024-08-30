package com.eCom.dhailer.util;

import com.eCom.dhailer.repo.ProductCategoryRepo;
import com.eCom.dhailer.repo.SupplierRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomKeyGenerator {

    private final ProductCategoryRepo productCategoryRepo;
    private final SupplierRepo supplierRepo;


    public String generateCategoryCode() {

        List<String> allByCategoryCode = productCategoryRepo.findAllByCategoryCode();

        String key = generateKey("C");

        if (allByCategoryCode.contains(key)) key = generateCategoryCode();

        return key;

    }

    public String generateSupplierCode() {

        List<String> allByCategoryCode = supplierRepo.findAllByCategoryCode();

        String key = generateKey("SP");

        if (allByCategoryCode.contains(key)) key = generateCategoryCode();

        return key;

    }


    public String generateKey(String starttext){


        Random random = new Random();

        String randomNumberPre = String.format("%04d", random.nextInt(10000));

        char randomChar = (char) ('A' + random.nextInt(26));

        String randomNumberPost = String.format("%02d", random.nextInt(100));

        return starttext + randomNumberPre + randomChar + randomNumberPost + "#";
    }


}
