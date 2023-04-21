package com.zhbit.noticebackend.exception.user;

public class UsernameEmptyException extends Exception{
    public UsernameEmptyException() {
        super("Username can not be empty");
    }

    public UsernameEmptyException(String message) {
        super(message);
    }
}
