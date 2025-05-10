package com.anirudh.portfolio.aniapp.exception;

public class ProfileNotFoundException extends Exception {
    private final String message;

    public ProfileNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
