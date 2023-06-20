package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.Advantage;
import javax.persistence.*;

@Converter(autoApply = true)
public class AdvantageConverter implements AttributeConverter<Advantage, String> {
    @Override
    public String convertToDatabaseColumn(Advantage attribute) {
        return attribute.toString();
    }

    @Override
    public Advantage convertToEntityAttribute(String dbData) {
        return Advantage.valueOf(dbData);
    }
}
