package com.zhbit.noticebackend.exception.notice_type;

public class NoticeTypeNotFoundException extends Exception{
    public NoticeTypeNotFoundException(){
        super("The specified NoticeType could not be found.");
    }
    public NoticeTypeNotFoundException(String message) {
        super(message);
    }
}
