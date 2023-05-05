package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.TypeApartment;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TypeApartmentConverter implements AttributeConverter<TypeApartment, String> {

    @Override
    public String convertToDatabaseColumn(TypeApartment attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }


    @Override
    public TypeApartment convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(TypeApartment.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
