package com.assignment.InternshipProject.model;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class HttpResponse {
    protected String timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected Map<?, ?> data;
    protected String code;

    public HttpResponse(int statusCode, HttpStatus status, String timeStamp, String message, Map<?, ?> data, ResponseCode responseCode) {
        this.statusCode = statusCode;
        this.status = status;
        this.timeStamp = timeStamp;
        this.message = message;
        this.data = data;
        this.code = responseCode.getCode();
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "timeStamp='" + timeStamp + '\'' +
                ", statusCode=" + statusCode +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", code='" + code + '\'' +
                '}';
    }
}
