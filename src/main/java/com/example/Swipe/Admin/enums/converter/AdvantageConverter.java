package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.Advantage;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
