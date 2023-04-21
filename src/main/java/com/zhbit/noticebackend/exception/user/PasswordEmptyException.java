package com.zhbit.noticebackend.exception.user;

public class PasswordEmptyException extends Exception{
    public PasswordEmptyException() {
        super("Password can not be empty");
    }

    public PasswordEmptyException(String message) {
        super(message);
    }
}
