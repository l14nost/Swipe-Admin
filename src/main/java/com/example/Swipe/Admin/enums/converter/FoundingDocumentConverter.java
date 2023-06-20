package com.example.Swipe.Admin.enums.converter;

import com.example.Swipe.Admin.enums.Calculation;
import com.example.Swipe.Admin.enums.FoundingDocument;
import javax.persistence.*;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class FoundingDocumentConverter implements AttributeConverter<FoundingDocument, String> {

    @Override
    public String convertToDatabaseColumn(FoundingDocument attribute) {
        if(attribute == null){
            return null;
        }
        else {
            return attribute.getValue();
        }
    }

    @Override
    public FoundingDocument convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(FoundingDocument.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
