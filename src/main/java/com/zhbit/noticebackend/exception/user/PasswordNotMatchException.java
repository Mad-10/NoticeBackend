package com.zhbit.noticebackend.exception.user;

public class PasswordNotMatchException extends Exception {

    public PasswordNotMatchException() {
        super("Password not match");
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
