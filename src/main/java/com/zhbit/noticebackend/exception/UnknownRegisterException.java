package com.zhbit.noticebackend.exception;

public class UnknownRegisterException extends Exception{
    public UnknownRegisterException() {
        super("The username and password meet the requirements, and the username has not been registered, but registration failed for unknown reasons!");
    }
    UnknownRegisterException(String message) {
        super(message);
    }
}
