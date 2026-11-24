package com.fatih.fegp.common.error.domain;

public class ResourceNotFoundException extends BusinessRuleException {
    public ResourceNotFoundException(String code, String message) {
        super(code, message);
    }
}
