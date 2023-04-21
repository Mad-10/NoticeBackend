package com.zhbit.noticebackend.exception.user;

public class PasswordNotMatchException extends Exception {
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
