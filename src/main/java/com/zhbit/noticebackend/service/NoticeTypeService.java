package com.zhbit.noticebackend.service;

import com.zhbit.noticebackend.entity.NoticeType;
import com.zhbit.noticebackend.exception.notice_type.CanNotReceiveNoticeTypeObjectException;
import com.zhbit.noticebackend.exception.notice_type.NoticeTypeAlreadyExistsException;
import com.zhbit.noticebackend.exception.notice_type.TypeNameEmptyException;
import com.zhbit.noticebackend.mapper.NoticeRecordMapper;
import com.zhbit.noticebackend.mapper.NoticeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeTypeService {


    private final NoticeTypeMapper noticeTypeMapper;

    private final NoticeRecordMapper noticeRecordMapper;

    @Autowired
    public NoticeTypeService(NoticeTypeMapper noticeTypeMapper, NoticeRecordMapper noticeRecordMapper) {
        this.noticeTypeMapper = noticeTypeMapper;
        this.noticeRecordMapper = noticeRecordMapper;
    }

    public NoticeType findById(Integer id) {
        return noticeTypeMapper.findById(id);
    }

    public NoticeType findByTypeName(String typeName) {
        return noticeTypeMapper.findByTypeName(typeName);
    }

    public List<NoticeType> findAll() {
        return noticeTypeMapper.findAll();
    }

    public void save(NoticeType noticeType) throws CanNotReceiveNoticeTypeObjectException, TypeNameEmptyException, NoticeTypeAlreadyExistsException {
        if (noticeType == null) {
            throw new CanNotReceiveNoticeTypeObjectException();
        }
        if (noticeType.getTypeName() == null) {
            throw new TypeNameEmptyException();
        }
        NoticeType tmpNoticeType = findByTypeName(noticeType.getTypeName());
        if (tmpNoticeType == null) {
            noticeTypeMapper.save(noticeType);
        } else {
            throw new NoticeTypeAlreadyExistsException();
        }
    }

    public void update(NoticeType noticeType) {
        noticeTypeMapper.update(noticeType);
    }

    public void deleteById(Integer id) {
        noticeTypeMapper.deleteById(id);
    }

    public void deleteByTypeName(String typeName) {
        noticeTypeMapper.deleteByTypeName(typeName);
    }

}
