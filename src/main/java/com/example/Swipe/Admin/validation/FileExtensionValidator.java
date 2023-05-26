package com.example.Swipe.Admin.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, Object> {

    private String[] allowedExtensions;

    @Override
    public void initialize(FileExtension constraintAnnotation) {
        allowedExtensions = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (value instanceof List) {
            List<MultipartFile> files = (List<MultipartFile>) value;
            return isValidFileList(files);
        } else if (value instanceof MultipartFile) {
            MultipartFile file = (MultipartFile) value;
            return isValidSingleFile(file);
        }

        return true;
    }

    private boolean isValidFileList(List<MultipartFile> files) {


        if (files.isEmpty()) {
            return true;
        }


        for (MultipartFile file : files) {
            if (!isFileExtensionAllowed(file)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidSingleFile(MultipartFile file) {
        return file.isEmpty() || isFileExtensionAllowed(file);
    }

    private boolean isFileExtensionAllowed(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename.length()>3) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            for (String allowedExtension : allowedExtensions) {
                if (allowedExtension.equalsIgnoreCase(fileExtension)) {
                    return true;
                }
            }
        }
        return false;
    }
}
