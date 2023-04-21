package com.zhbit.noticebackend.exception.notice_type;

public class CanNotReceiveNoticeTypeObjectException extends Exception{
    public CanNotReceiveNoticeTypeObjectException() {
        super("No NoticeType object received.");
    }

    public CanNotReceiveNoticeTypeObjectException(String message) {
        super(message);
    }
}
