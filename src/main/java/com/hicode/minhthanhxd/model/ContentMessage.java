package com.hicode.minhthanhxd.model;

import java.io.File;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class ContentMessage {
    private String to;
    private String subject;
    private String body;
    private MultipartFile attachment;
}
