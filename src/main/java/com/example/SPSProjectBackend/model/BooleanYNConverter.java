package com.example.SPSProjectBackend.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class BooleanYNConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (value == null) return null;
        return value ? "Y" : "N";
    }
    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return "Y".equalsIgnoreCase(dbValue);
    }
}
