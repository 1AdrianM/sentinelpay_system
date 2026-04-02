package com.github.sentinel.pay.infrastructure.out.persistence.jpa.attributeConverter;

import java.util.HashMap;
import java.util.Map;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sentinel.pay.domain.entity.shared.Location;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
@Converter
@RequiredArgsConstructor
public class LocationMapConverter implements AttributeConverter<Map<Location,Integer>, String> {
      private final ObjectMapper objectMapper;
    @Override
    public String convertToDatabaseColumn(Map<Location, Integer> attribute) {
        if(attribute == null || attribute.isEmpty()){
            return "{}";
        }
        try {
            Map<String,Integer> newMap = new HashMap<>();
            for(var entry: attribute.entrySet()){
              newMap.put(entry.getKey().toString(), entry.getValue());
            }
          return objectMapper.writeValueAsString(newMap);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Map to JSON", e);
        }
      }

    @Override
    public Map<Location, Integer> convertToEntityAttribute(String dbData) {
        try{
         Map<String,Integer> countJsonMap=  objectMapper.readValue(dbData, new TypeReference<Map<String,Integer>>(){});
        Map<Location,Integer> resultMap= new HashMap<>();
         for(var entry: countJsonMap.entrySet()){
         String[] parts=  entry.getKey().split(",");
         if (parts.length==2){
            resultMap.put(Location.of(parts[0], parts[1]),
             entry.getValue()
        );
    }
}
           return resultMap;            
        }catch(JsonProcessingException e){
            throw new RuntimeException("error",e);
        }
    }

}