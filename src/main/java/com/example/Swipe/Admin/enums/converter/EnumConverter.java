//package com.example.Swipe.Admin.enums.converter;
//
//import com.example.Swipe.Admin.enums.Calculation;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//import java.util.stream.Stream;
//
//@Converter(autoApply = true)
//public class EnumConverter implements AttributeConverter<Enum, String> {
//
//    @Override
//    public String convertToDatabaseColumn(Enum attribute) {
//        if(attribute == null){
//            return null;
//        }
//        else {
//            return attribute.name();
//        }
//    }
//
//    @Override
//    public Enum convertToEntityAttribute(String dbData) {
//        if (dbData == null) {
//            return null;
//        }
//
//        return Enum.valueOf(Enum.class, dbData);
//
//    }
//}

