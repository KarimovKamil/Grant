package ru.itis.grant.security.exception;

public class IncorrectDataException extends RuntimeException {
    private String fieldName;

    public IncorrectDataException(String fieldName, String string){
        super(string);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
