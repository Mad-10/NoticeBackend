package com.zhbit.noticebackend.exception;

public class UsernameEmptyException extends Exception{
    public UsernameEmptyException() {
        super("Username can not be empty");
    }

    public UsernameEmptyException(String message) {
        super(message);
    }
}
