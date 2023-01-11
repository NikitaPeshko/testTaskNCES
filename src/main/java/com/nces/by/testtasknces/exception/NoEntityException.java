package com.nces.by.testtasknces.exception;

public class NoEntityException extends Exception{
    private String code;

    public NoEntityException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getErrorcode() {
        return code;
    }
}
