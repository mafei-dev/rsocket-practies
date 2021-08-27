package com.mafei.rsocket.practies.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;

import java.io.IOException;

public class ObjectUtil {
    public static Payload toPayload(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] asBytes = objectMapper.writeValueAsBytes(object);
            return DefaultPayload.create(asBytes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(Payload payload, Class<T> type) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] array = payload.getData().array();
            return objectMapper.readValue(array, type);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
