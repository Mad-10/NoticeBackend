package com.zhbit.noticebackend.mapper;

import com.zhbit.noticebackend.entity.NoticeRecord;
import com.zhbit.noticebackend.entity.NoticeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeRecordMapperTest {
    @Autowired
    private NoticeRecordMapper noticeRecordMapper;

    @Test
    public void testFindById() {
        NoticeRecord noticeRecord = noticeRecordMapper.findById(1);
        System.out.println(noticeRecord);
        assertNotNull(noticeRecord);
        assertEquals("课表查询通知", noticeRecord.getTitle());
        assertNotNull(noticeRecord.getNoticeType());
        assertEquals("教学通知", noticeRecord.getNoticeType().getTypeName());
    }

    @Test
    public void testFindAll() {
        List<NoticeRecord> noticeRecordList = noticeRecordMapper.findAll();
        System.out.println(noticeRecordList);
        assertNotNull(noticeRecordList);
        assertFalse(noticeRecordList.isEmpty());
    }

    @Test
    public void testSave() {
        NoticeRecord noticeRecord = new NoticeRecord();
        noticeRecord.setTitle("测试标题");
        noticeRecord.setContent("测试内容");
        noticeRecord.setEditor("测试编辑");
        noticeRecord.setCreateTime(new Date());
        noticeRecord.setTypeId(1);
        noticeRecordMapper.save(noticeRecord);
        assertNotNull(noticeRecord.getId());

        NoticeRecord savedNoticeRecord = noticeRecordMapper.findById(noticeRecord.getId());
        System.out.println(savedNoticeRecord);
        assertNotNull(savedNoticeRecord);
        assertEquals(noticeRecord.getTitle(), savedNoticeRecord.getTitle());
        assertEquals(noticeRecord.getContent(), savedNoticeRecord.getContent());
        assertEquals(noticeRecord.getEditor(), savedNoticeRecord.getEditor());
//        assertTrue(noticeRecord.getCreateTime().equals(savedNoticeRecord.getCreateTime()));
        assertEquals(noticeRecord.getTypeId(), savedNoticeRecord.getTypeId());
    }

    @Test
    public void testUpdate() {
        NoticeRecord noticeRecord = noticeRecordMapper.findById(1);
        assertNotNull(noticeRecord);
        String newTitle = "测试标题2";
        noticeRecord.setTitle(newTitle);
        noticeRecordMapper.update(noticeRecord);

        NoticeRecord updatedNoticeRecord = noticeRecordMapper.findById(1);
        assertNotNull(updatedNoticeRecord);
        assertEquals(newTitle, updatedNoticeRecord.getTitle());
    }

    @Test
    public void testDeleteById() {
        noticeRecordMapper.deleteById(2);
        NoticeRecord deletedNoticeRecord = noticeRecordMapper.findById(2);
        assertNull(deletedNoticeRecord);
    }

}