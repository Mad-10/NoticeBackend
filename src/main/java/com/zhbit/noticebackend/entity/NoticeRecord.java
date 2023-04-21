package com.zhbit.noticebackend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeRecord {
    private Integer id;
    private String title;
    private String content;
    private String editor;
    private Date createTime;
    private Integer typeId;
    private NoticeType noticeType;
}