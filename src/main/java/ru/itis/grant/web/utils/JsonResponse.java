package ru.itis.grant.web.utils;

public class JsonResponse {
    private String field;
    private String message;

    public JsonResponse() {
    }

    public JsonResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}