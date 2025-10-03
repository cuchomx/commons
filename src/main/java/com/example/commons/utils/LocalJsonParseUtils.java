package com.example.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocalJsonParseUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Serialize a DTO to JSON.
     */
    public static <T> Optional<String> toJson(T dto) {
        if (dto == null) {
            log.warn("LocalJsonParseUtils::toJson - received null dto");
            return Optional.empty();
        }
        try {
            return Optional.of(MAPPER.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            log.error("LocalJsonParseUtils::toJson - Error serializing", e);
            return Optional.empty();
        }
    }

    /**
     * Deserialize a JSON array into a List of elementType.
     */
    public static <T> Optional<List<T>> fromJsonList(String json, Class<T> elementType) {
        if (json == null || json.isBlank()) {
            log.warn("LocalJsonParseUtils::fromJsonList - received null/blank json");
            return Optional.empty();
        }
        if (elementType == null) {
            log.error("LocalJsonParseUtils::fromJsonList - elementType is null");
            return Optional.empty();
        }
        try {
            var typeRef = new TypeReference<List<T>>() {};
            var javaType = MAPPER.getTypeFactory().constructCollectionType(List.class, elementType);
            return Optional.of(MAPPER.readValue(json, javaType));
        } catch (Exception e) {
            log.error("LocalJsonParseUtils::fromJsonList - Failed to parse", e);
            return Optional.empty();
        }
    }
}
