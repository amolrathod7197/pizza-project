package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class CustomErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonProperty("TimeStamp")
    private LocalDateTime timestamp;
    @JsonProperty("Status")
    private int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Error")
    private String error;
    @JsonProperty("Errors")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List errors;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List getErrors() {
        return errors;
    }

    public void setErrors(List errors) {
        this.errors = errors;
    }
}
