package com.eCom.dhailer.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Block;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonFileSavedBinaryDataDto {

    private Blob hash;
    private String directory;
    private Blob filename;
    private Blob resourceurl;
}
