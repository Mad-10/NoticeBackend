package com.zhbit.noticebackend.service;

import com.zhbit.noticebackend.entity.NoticeRecord;
import com.zhbit.noticebackend.mapper.NoticeRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeRecordService {

    private final NoticeRecordMapper noticeRecordMapper;

    @Autowired
    public NoticeRecordService(NoticeRecordMapper noticeRecordMapper) {
        this.noticeRecordMapper = noticeRecordMapper;
    }

    public NoticeRecord findById(Integer id) {
        return noticeRecordMapper.findById(id);
    }

    public List<NoticeRecord> findAll() {
        return noticeRecordMapper.findAll();
    }

    public void save(NoticeRecord noticeRecord) {
        noticeRecordMapper.save(noticeRecord);
    }

    public void update(NoticeRecord noticeRecord) {
        noticeRecordMapper.update(noticeRecord);
    }

    public void deleteById(Integer id) {
        noticeRecordMapper.deleteById(id);
    }

    public List<NoticeRecord> findByNoticeType(Integer typeId) {
        return noticeRecordMapper.findByNoticeType(typeId);
    }

}
