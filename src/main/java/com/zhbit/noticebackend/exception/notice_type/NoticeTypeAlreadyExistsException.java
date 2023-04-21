package com.zhbit.noticebackend.exception.notice_type;

public class NoticeTypeAlreadyExistsException extends Exception{
    public NoticeTypeAlreadyExistsException() {
        super("NoticeType already exists");
    }

    public NoticeTypeAlreadyExistsException(String message) {
        super(message);
    }
}
