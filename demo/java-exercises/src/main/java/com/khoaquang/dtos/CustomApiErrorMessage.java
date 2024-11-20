package com.khoaquang.dtos;

public class CustomApiErrorMessage {
    private int status;
    private String message;

    public CustomApiErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
