package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class PhotoDTO {

     int idPhotos;

     String fileName;
     MultipartFile fileGallery;

     LcdDTO lcd;

     ApartmentDTO apartment;
}
