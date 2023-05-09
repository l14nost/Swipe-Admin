package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.ClassType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ClassTypeConverter implements AttributeConverter<ClassType, String> {

    @Override
    public String convertToDatabaseColumn(ClassType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public ClassType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(ClassType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
