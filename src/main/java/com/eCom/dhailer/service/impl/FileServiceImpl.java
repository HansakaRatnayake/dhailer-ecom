package com.eCom.dhailer.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.eCom.dhailer.service.FileService;
import com.eCom.dhailer.util.CommonFileSavedBinaryDataDto;
import com.eCom.dhailer.util.ImageUploadGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final AmazonS3 s3;
    private final AmazonS3Client s3Client;
    private final ImageUploadGenerator imageUploadGenerator;


    @Override
    public CommonFileSavedBinaryDataDto createResource(MultipartFile file, String directory, String bucket) {

        try{

            String originalFilename = file.getOriginalFilename();
            String newfilename = imageUploadGenerator.genarateDHailerResourceName(originalFilename, UUID.randomUUID().toString());
            PutObjectResult putObjectResult = s3Client.putObject(new PutObjectRequest(bucket, directory + "" + newfilename, file.getInputStream(),
                    new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));

            return new CommonFileSavedBinaryDataDto(
                    new SerialBlob(putObjectResult.getContentMd5().getBytes()),
                    directory,
                    new SerialBlob(newfilename.getBytes()),
                    new SerialBlob(s3Client.getResourceUrl(bucket, directory + newfilename).getBytes()));

        }catch (SQLException | IOException exception){
            throw new RuntimeException();
        }

     }

    @Override
    public void deleteResource(String bucket, String directory, String filename) {
        s3Client.deleteObject(bucket, directory + filename);
    }

    @Override
    public byte[] downloadFile(String bucket, String filename) {
        S3Object object = s3.getObject(bucket, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
