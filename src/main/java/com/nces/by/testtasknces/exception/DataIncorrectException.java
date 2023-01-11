package com.nces.by.testtasknces.exception;

public class DataIncorrectException extends Exception{
    private String code;

    public DataIncorrectException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getErrorcode() {
        return code;
    }
}
