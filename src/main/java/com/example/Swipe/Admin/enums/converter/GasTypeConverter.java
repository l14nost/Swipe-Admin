package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.GasType;
import com.example.Swipe.Admin.enums.StatusLCDType;
import javax.persistence.*;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class GasTypeConverter implements AttributeConverter<GasType, String> {

    @Override
    public String convertToDatabaseColumn(GasType attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public GasType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(GasType.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
