package com.zhbit.noticebackend.service;

import com.zhbit.noticebackend.mapper.NoticeRecordMapper;
import com.zhbit.noticebackend.mapper.NoticeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeTypeService {
    @Autowired
    private final NoticeTypeMapper noticeTypeMapper;

    @Autowired
    private final NoticeRecordMapper noticeRecordMapper;

    public NoticeTypeService(NoticeTypeMapper noticeTypeMapper, NoticeRecordMapper noticeRecordMapper) {
        this.noticeTypeMapper = noticeTypeMapper;
        this.noticeRecordMapper = noticeRecordMapper;
    }

}
