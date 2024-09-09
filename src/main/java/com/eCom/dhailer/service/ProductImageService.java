package com.eCom.dhailer.service;

import com.eCom.dhailer.dto.responce.ResponceProductImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductImageService {

    public void create(MultipartFile file, String productId) throws SQLException, IOException;
    public ResponceProductImageDto findById(String id);
    public void delete(String id);

}
