package com.example.Swipe.Admin.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class DocumentDTO {
     int idDocuments;
     String name;
     String fileName;
     MultipartFile fileDocument;
     LcdDTO lcd;
}
