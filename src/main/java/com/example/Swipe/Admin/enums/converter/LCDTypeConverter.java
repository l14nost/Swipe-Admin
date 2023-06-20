package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.ClassType;
import com.example.Swipe.Admin.enums.LCDType;
import javax.persistence.*;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class LCDTypeConverter implements AttributeConverter<LCDType, String> {

    @Override
    public String convertToDatabaseColumn(LCDType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public LCDType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(LCDType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
