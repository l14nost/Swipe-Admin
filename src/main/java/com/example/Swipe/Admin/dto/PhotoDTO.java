package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.validation.FileExtension;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class PhotoDTO {

     int idPhotos;

     String fileName;
     @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
     MultipartFile fileGallery;

     LcdDTO lcd;

     ApartmentDTO apartment;
}
