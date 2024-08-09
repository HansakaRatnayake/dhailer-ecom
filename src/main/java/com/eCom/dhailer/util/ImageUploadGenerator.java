package com.eCom.dhailer.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageUploadGenerator {

    public String genarateDHailerResourceName(String name,String type){

        StringBuilder stringBuilder  = new StringBuilder();
        stringBuilder.append(UUID.randomUUID().toString());
        stringBuilder.append("-DH-");
        stringBuilder.append(type).append("-");
        stringBuilder.append(name);

        return stringBuilder.toString();
    }

}
