package com.vibe.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [Technical] JSON 蹂???듯빀 ?좏떥由ы떚
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // Java 8 ?좎쭨 吏??
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // ISO-8601 ?뺤떇

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("JSON Serialization Error", e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("JSON Deserialization Error", e);
            return null;
        }
    }
}

