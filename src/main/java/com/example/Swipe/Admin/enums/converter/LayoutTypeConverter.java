package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.BalconyType;
import com.example.Swipe.Admin.enums.LayoutType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class LayoutTypeConverter implements AttributeConverter<LayoutType, String> {

    @Override
    public String convertToDatabaseColumn(LayoutType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public LayoutType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(LayoutType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
