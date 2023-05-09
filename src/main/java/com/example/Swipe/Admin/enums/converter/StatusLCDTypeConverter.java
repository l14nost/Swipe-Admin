package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.BalconyType;
import com.example.Swipe.Admin.enums.StatusLCDType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusLCDTypeConverter implements AttributeConverter<StatusLCDType, String> {

    @Override
    public String convertToDatabaseColumn(StatusLCDType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public StatusLCDType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(StatusLCDType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
