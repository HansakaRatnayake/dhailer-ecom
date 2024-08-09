package com.eCom.dhailer.service;


import com.eCom.dhailer.util.CommonFileSavedBinaryDataDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public CommonFileSavedBinaryDataDto createResource(MultipartFile file, String directory, String bucket);
    public void deleteResource(String bucket, String directory, String filename);

    public byte[] downloadFile(String bucket, String filename);
}
