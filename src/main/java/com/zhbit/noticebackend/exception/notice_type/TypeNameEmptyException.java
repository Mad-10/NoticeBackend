package com.zhbit.noticebackend.exception.notice_type;

public class TypeNameEmptyException extends Exception{
    public TypeNameEmptyException() {
        super("TypeName can not be empty.");
    }

    public TypeNameEmptyException(String message) {
        super(message);
    }
}
