package com.weekendinc.baseapp.model.api.deserializer;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.weekendinc.baseapp.helper.WLog;
import com.weekendinc.baseapp.model.api.data.Error;

import java.io.IOException;

/**
 * Created by Fadhlan on 4/18/17.
 */

public class ErrorEmptyAsNullDeserializer extends JsonDeserializer<Error> {

    private static final String LOG_TAG = ErrorEmptyAsNullDeserializer.class.getSimpleName();

    @Override public Error deserialize(JsonParser p,
                                       DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        WLog.e(LOG_TAG, "deserialize: ERROR NODE: "+ node.toString());
        if (node.size() == 0) {
            return null;
        }
        Error error = new Error();
        error.setCode(node.get("code").asInt());
        error.setMessage(node.get("message").asText());
        return error;
    }
}
