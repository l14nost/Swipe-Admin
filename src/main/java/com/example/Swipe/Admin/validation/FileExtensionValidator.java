package com.example.Swipe.Admin.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {
    private String[] allowedExtensions;

    @Override
    public void initialize(FileExtension constraintAnnotation) {
        allowedExtensions = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true;  // Пустые файлы допустимы, если это требуется
        }

        String fileExtension = getFileExtension(file.getOriginalFilename());
        return Arrays.stream(allowedExtensions)
                .anyMatch(extension -> extension.equalsIgnoreCase(fileExtension));
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
}
