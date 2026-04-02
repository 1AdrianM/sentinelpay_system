package com.github.sentinel.pay.infrastructure.out.persistence.jpa.attributeConverter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sentinel.pay.domain.entity.shared.Currency;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class CurrencyMapConverter implements AttributeConverter<Map<Currency, Integer>, String> {
    private final ObjectMapper objectMapper;
    @Override
    public String convertToDatabaseColumn(Map<Currency, Integer> attribute) {
    
    try{
         Map<String,Integer> jsonMap = new HashMap<>();
        for(var entry:attribute.entrySet()){
            jsonMap.put(entry.getKey().name(), Integer.valueOf(entry.getValue()));
        }
      return objectMapper.writeValueAsString(jsonMap);
    }catch(JsonProcessingException e){

        throw new RuntimeException(e);
    }

    }

    @Override
    public Map<Currency, Integer> convertToEntityAttribute(String dbData) {
    
        try{
         Map<String,Integer> countJsonMap=  objectMapper.readValue(dbData, new com.fasterxml.jackson.core.type.TypeReference<Map<String,Integer>>(){});
        Map<Currency,Integer> resultMap= new HashMap<>();
         for(var entry: countJsonMap.entrySet()){
         resultMap.put(Currency.valueOf(entry.getKey()), entry.getValue());
        }
        return resultMap;
    }catch(JsonProcessingException e){
            throw new RuntimeException("error",e);
        }
    }   


    
}
