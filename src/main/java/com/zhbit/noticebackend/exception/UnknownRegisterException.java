package com.zhbit.noticebackend.exception;

public class UnknownRegisterException extends Exception{
    public UnknownRegisterException() {
        super("用户名和密码都符合要求，且用户名未被注册，但因未知原因注册失败！");
    }
    UnknownRegisterException(String message) {
        super(message);
    }
}
