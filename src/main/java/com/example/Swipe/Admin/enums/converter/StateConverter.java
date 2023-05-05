package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.Calculation;
import com.example.Swipe.Admin.enums.State;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<State, String> {

    @Override
    public String convertToDatabaseColumn(State attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public State convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(State.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
