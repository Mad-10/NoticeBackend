package com.zhbit.noticebackend.entity;

import lombok.Data;

import java.util.List;

@Data
public class NoticeTypeDto {
    List<NoticeType> noticeTypes;
    String message;
}
