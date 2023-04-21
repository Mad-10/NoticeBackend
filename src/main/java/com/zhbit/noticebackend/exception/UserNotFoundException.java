package com.zhbit.noticebackend.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User has not been registered.");
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
