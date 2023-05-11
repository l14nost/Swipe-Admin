//package com.example.Swipe.Admin.enums.converter;
//
//import com.example.Swipe.Admin.enums.BalconyType;
//import com.example.Swipe.Admin.enums.Role;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//import java.util.stream.Stream;
//
//@Converter(autoApply = true)
//public class RoleConverter implements AttributeConverter<Role, String> {
//
//    @Override
//    public String convertToDatabaseColumn(Role attribute) {
//        if(attribute == null){
//            return null;
//        }
//        else {
//            return attribute.getValue();
//        }
//    }
//
//    @Override
//    public Role convertToEntityAttribute(String dbData) {
//        if (dbData == null) {
//            return null;
//        }
//
//        return Stream.of(Role.values())
//                .filter(c -> c.getValue().equals(dbData))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new);
//
//    }
//}
