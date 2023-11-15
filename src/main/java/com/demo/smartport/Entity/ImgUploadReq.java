package com.demo.smartport.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImgUploadReq implements Serializable {
    private String message;
    private String content;
}
