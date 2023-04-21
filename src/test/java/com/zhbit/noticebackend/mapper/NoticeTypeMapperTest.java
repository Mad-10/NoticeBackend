package com.zhbit.noticebackend.mapper;

import com.zhbit.noticebackend.entity.NoticeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoticeTypeMapperTest {

    @Autowired
    private NoticeTypeMapper noticeTypeMapper;

    @Test
    public void testFindById() {
        NoticeType noticeType = noticeTypeMapper.findById(2);
        System.out.println(noticeType);
        assertNotNull(noticeType);
        assertEquals("科研通知", noticeType.getTypeName());
    }

    @Test
    public void testFindByTypeName() {
        NoticeType noticeType = noticeTypeMapper.findByTypeName("科研通知");
        assertNotNull(noticeType);
        assertEquals(2, noticeType.getId());
    }

    @Test
    public void testFindAll() {
        List<NoticeType> noticeTypes = noticeTypeMapper.findAll();
        assertNotNull(noticeTypes);
        assertEquals(4, noticeTypes.size());
    }

    @Test
    public void testSave() {
        NoticeType noticeType = new NoticeType();
        noticeType.setTypeName("测试类型");
        noticeTypeMapper.save(noticeType);
        assertNotNull(noticeType.getId());

        NoticeType savedNoticeType = noticeTypeMapper.findById(noticeType.getId());
        assertNotNull(savedNoticeType);
        assertEquals("测试类型", savedNoticeType.getTypeName());
    }

    @Test
    public void testUpdate() {
        NoticeType noticeType = new NoticeType();
        noticeType.setId(5);
        noticeType.setTypeName("新类型名称");
        noticeTypeMapper.update(noticeType);

        NoticeType updatedNoticeType = noticeTypeMapper.findById(5);
        assertNotNull(updatedNoticeType);
        assertEquals("新类型名称", updatedNoticeType.getTypeName());
    }

    @Test
    public void testDeleteById() {
        noticeTypeMapper.deleteById(5);
        NoticeType deletedNoticeType = noticeTypeMapper.findById(5);
        assertNull(deletedNoticeType);
    }

    @Test
    public void testDeleteByTypeName() {
        noticeTypeMapper.deleteByTypeName("测试类型");
        NoticeType deletedNoticeType = noticeTypeMapper.findByTypeName("测试类型");
        assertNull(deletedNoticeType);
    }
}
