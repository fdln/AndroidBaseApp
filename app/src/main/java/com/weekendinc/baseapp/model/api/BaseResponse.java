package com.weekendinc.baseapp.model.api;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weekendinc.baseapp.model.api.data.Error;
import com.weekendinc.baseapp.model.api.deserializer.ErrorEmptyAsNullDeserializer;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties (ignoreUnknown = true)
public class BaseResponse<T> {

    @JsonProperty ("apiVersion")
    private String apiVersion;
    @JsonProperty ("memoryUsage")
    private String memoryUsage;
    @JsonProperty ("elapseTime")
    private double elapseTime;
    @JsonProperty ("lang")
    private String lang;
    @JsonProperty ("currency")
    private String currency;


    @JsonProperty ("accessToken")
    private String accessToken;
    @JsonProperty ("refreshToken")
    private String refreshToken;

//    @JsonInclude (JsonInclude.Include.NON_EMPTY)
    @Nullable @JsonProperty ("data")
    private T t;

    @JsonProperty ("error")
    @JsonDeserialize (using = ErrorEmptyAsNullDeserializer.class)
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Error error;

    /**
     * @return The apiVersion
     */
    @JsonProperty ("api_version")
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion The api_version
     */
    @JsonProperty ("api_version")
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * @return The memoryUsage
     */
    @JsonProperty ("memory_usage")
    public String getMemoryUsage() {
        return memoryUsage;
    }

    /**
     * @param memoryUsage The memory_usage
     */
    @JsonProperty ("memory_usage")
    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    /**
     * @return The elapseTime
     */
    @JsonProperty ("elapse_time")
    public double getElapseTime() {
        return elapseTime;
    }

    /**
     * @param elapseTime The elapse_time
     */
    @JsonProperty ("elapse_time")
    public void setElapseTime(double elapseTime) {
        this.elapseTime = elapseTime;
    }

    /**
     * @return The lang
     */
    @JsonProperty ("lang")
    public String getLang() {
        return lang;
    }

    /**
     * @param lang The lang
     */
    @JsonProperty ("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return The currency
     */
    @JsonProperty ("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    @JsonProperty ("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return The error
     */
    @JsonProperty ("error")
    public Error getError() {
        return error;
    }

    /**
     * @param error The error
     */
    @JsonProperty ("error")
    public void setError(Error error) {
        this.error = error;
    }


    /**
     * @return The accessToken
     */
    @JsonProperty ("accessToken")
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken The access_token
     */
    @JsonProperty ("accessToken")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    /**
     * @return The refreshToken
     */
    @JsonProperty ("refreshToken")
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken The refresh_token
     */
    @JsonProperty ("refreshToken")
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Nullable @JsonProperty ("data")
    public T getData() {
        return t;
    }

    @JsonProperty ("data")
    public void setData(@Nullable T t) {
        this.t = t;
    }


    @Override
    public String toString() {
        return "BaseResponse{" +
                "apiVersion='" + apiVersion + '\'' +
                ", memoryUsage='" + memoryUsage + '\'' +
                ", elapseTime=" + elapseTime +
                ", lang='" + lang + '\'' +
                ", currency='" + currency + '\'' +
                ", error=" + error +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}