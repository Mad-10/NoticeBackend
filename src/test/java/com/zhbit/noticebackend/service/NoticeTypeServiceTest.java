package com.zhbit.noticebackend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.zhbit.noticebackend.entity.NoticeType;
import com.zhbit.noticebackend.exception.notice_type.CanNotReceiveNoticeTypeObjectException;
import com.zhbit.noticebackend.exception.notice_type.NoticeTypeAlreadyExistsException;
import com.zhbit.noticebackend.exception.notice_type.TypeNameEmptyException;
import com.zhbit.noticebackend.mapper.NoticeTypeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NoticeTypeServiceTest {

    @Autowired
    private NoticeTypeMapper noticeTypeMapper;

    @Autowired
    private NoticeTypeService noticeTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindById() {
        // 定义要返回的NoticeType对象
        NoticeType noticeType = new NoticeType();
        noticeType.setId(1);
        noticeType.setTypeName("test");

        // 设置当noticeTypeMapper.findById(1)方法被调用时返回上面定义的noticeType对象
        when(noticeTypeMapper.findById(1)).thenReturn(noticeType);

        // 调用NoticeTypeService的findById方法
        NoticeType result = noticeTypeService.findById(1);

        // 验证结果是否正确
        assertEquals(noticeType.getId(), result.getId());
        assertEquals(noticeType.getTypeName(), result.getTypeName());

        // 验证noticeTypeMapper.findById(1)方法是否被调用
        verify(noticeTypeMapper).findById(1);
    }

    @Test
    void testFindAll() {
        //调用service层的方法
        List<NoticeType> noticeTypes = noticeTypeService.findAll();
        //判断查询结果是否为空
        assertNotNull(noticeTypes);
        //判断查询结果数量是否符合预期
        assertEquals(4, noticeTypes.size());
        for (NoticeType noticeType: noticeTypes
             ) {
            System.out.println(noticeType);
        }
    }

    @Test
    void saveTest() {
        NoticeType noticeType = new NoticeType();
        noticeType.setTypeName("测试类型");
        try {
            noticeTypeService.save(noticeType);
        } catch (CanNotReceiveNoticeTypeObjectException | TypeNameEmptyException | NoticeTypeAlreadyExistsException e) {
            e.printStackTrace();
        }

        // 通过id查询刚刚添加的公告类型
        NoticeType result = noticeTypeService.findById(noticeType.getId());
        assertNotNull(result);
        assertEquals(noticeType.getTypeName(), result.getTypeName());
    }


    @Test
    @DisplayName("测试根据类型名称查询公告类型")
    public void testFindByTypeName() {
        String typeName = "测试类型";
        // 调用被测试方法
        NoticeType result = noticeTypeService.findByTypeName(typeName);

        // 验证结果
        assertNotNull(result);
        assertEquals(typeName, result.getTypeName());
        assertEquals(7, result.getId());
    }

    @Test
    void testUpdate() {
        NoticeType noticeType = new NoticeType();
        noticeType.setId(7);
        noticeType.setTypeName("新测试类型");

        noticeTypeMapper.update(noticeType);
        NoticeType updatedType = noticeTypeMapper.findById(7);
        assertEquals(noticeType.getTypeName(), updatedType.getTypeName());
    }

    @Test
    void testDeleteById() {
        NoticeType type = noticeTypeMapper.findById(7);
        assertNotNull(type);

        noticeTypeMapper.deleteById(7);
        assertNull(noticeTypeMapper.findById(7));
    }

    @Test
    void testDeleteByTypeName() {
        String typeName = "测试类型";
        NoticeType type = noticeTypeMapper.findByTypeName(typeName);
        assertNotNull(type);

        noticeTypeMapper.deleteByTypeName(typeName);
        assertNull(noticeTypeMapper.findByTypeName(typeName));
    }



}