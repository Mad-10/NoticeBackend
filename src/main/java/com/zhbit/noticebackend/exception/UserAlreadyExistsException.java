package com.zhbit.noticebackend.exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("Username already exists");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
