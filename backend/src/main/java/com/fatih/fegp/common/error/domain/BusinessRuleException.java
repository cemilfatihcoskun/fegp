package com.fatih.fegp.common.error.domain;

public class BusinessRuleException extends RuntimeException {
    private String code;
    private String message;

    public BusinessRuleException(String code, String message) {
        super(message);

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
