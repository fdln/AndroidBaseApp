package com.weekendinc.baseapp.model.api.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weekendinc.baseapp.model.api.deserializer.ErrorEmptyAsNullDeserializer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@JsonIgnoreProperties (ignoreUnknown = true)
@JsonInclude (JsonInclude.Include.NON_NULL)
@Generated ("org.jsonschema2pojo")
@JsonPropertyOrder ({
        "code",
        "message",
        "errors"
})
public class Error {

    @JsonProperty ("code")
    private int code;
    @JsonProperty ("message")
    private String message;
    @JsonDeserialize (using = ErrorEmptyAsNullDeserializer.class)
    @JsonProperty ("errors")
    private List<Error> errors = new ArrayList<Error>();

    /**
     * @return The code
     */
    @JsonProperty ("code")
    public int getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    @JsonProperty ("code")
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return The message
     */
    @JsonProperty ("message")
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    @JsonProperty ("message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The errors
     */
    @JsonProperty ("errors")
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * @param errors The errors
     */
    @JsonProperty ("errors")
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
