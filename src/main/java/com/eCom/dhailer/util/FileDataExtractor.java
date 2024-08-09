package com.eCom.dhailer.util;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class FileDataExtractor {

    public byte[] blobToByteArray(Blob blob) throws IOException, SQLException {
        if (blob == null) return new byte[0];
        try (
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
            int content;
            while ((content = inputStream.read()) != -1) {
                byteArrayOutputStream.write((byte) content);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    public String byteArrayToString(byte[] bytearray){
        if (bytearray == null || bytearray.length == 0) return null;

        return new String(bytearray, StandardCharsets.UTF_8);
    }

    public String actualFileName(InputStreamReader inputStreamReader){

        try {

            String temp;

            StringBuffer buffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((temp = bufferedReader.readLine()) != null) buffer.append(temp);
            return buffer.toString();
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
