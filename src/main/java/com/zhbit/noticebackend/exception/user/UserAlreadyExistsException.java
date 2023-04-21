package com.zhbit.noticebackend.exception.user;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("Username already exists");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
