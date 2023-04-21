package com.zhbit.noticebackend.service;

import com.zhbit.noticebackend.entity.NoticeRecord;
import com.zhbit.noticebackend.mapper.NoticeRecordMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoticeRecordServiceTest {

    @Mock
    private NoticeRecordMapper noticeRecordMapper;

    @InjectMocks
    private NoticeRecordService noticeRecordService;

    @Test
    void shouldFindNoticeRecordById() {
        // Given
        NoticeRecord noticeRecord = new NoticeRecord();
        noticeRecord.setId(1);
        noticeRecord.setTitle("Test notice");

        when(noticeRecordMapper.findById(1)).thenReturn(noticeRecord);

        // When
        NoticeRecord result = noticeRecordService.findById(1);

        // Then
        assertThat(result).isEqualTo(noticeRecord);
        verify(noticeRecordMapper).findById(1);
    }

    @Test
    void shouldFindAllNoticeRecords() {
        // Given
        List<NoticeRecord> noticeRecords = new ArrayList<>();
        NoticeRecord noticeRecord = new NoticeRecord();
        noticeRecord.setId(7);
        noticeRecord.setTitle("Notice 7");
        noticeRecords.add(noticeRecord);
        noticeRecord.setId(8);
        noticeRecord.setTitle("Notice 8");
        noticeRecords.add(noticeRecord);

        when(noticeRecordMapper.findAll()).thenReturn(noticeRecords);

        // When
        List<NoticeRecord> result = noticeRecordService.findAll();

        // Then
        assertThat(result).isEqualTo(noticeRecords);
        verify(noticeRecordMapper).findAll();
    }

    @Test
    void shouldSaveNoticeRecord() {
        // Given
        NoticeRecord noticeRecord = new NoticeRecord();
        noticeRecord.setTitle("Test notice");

        // When
        noticeRecordService.save(noticeRecord);

        // Then
        verify(noticeRecordMapper).save(noticeRecord);
    }

    @Test
    void shouldUpdateNoticeRecord() {
        // Given
        NoticeRecord noticeRecord = new NoticeRecord();
        noticeRecord.setId(1);
        noticeRecord.setTitle("Test notice");

        // When
        noticeRecordService.update(noticeRecord);

        // Then
        verify(noticeRecordMapper).update(noticeRecord);
    }

    @Test
    void shouldDeleteNoticeRecordById() {
        // Given
        Integer id = 1;

        // When
        noticeRecordService.deleteById(id);

        // Then
        verify(noticeRecordMapper).deleteById(id);
    }

    @Test
    void shouldFindNoticeRecordsByNoticeType() {
        // Given
        Integer typeId = 1;
        List<NoticeRecord> noticeRecords = new ArrayList<>();
        NoticeRecord noticeRecord = new NoticeRecord();
        noticeRecord.setId(7);
        noticeRecord.setTitle("Notice 7");
        noticeRecords.add(noticeRecord);
        noticeRecord.setId(8);
        noticeRecord.setTitle("Notice 8");
        noticeRecords.add(noticeRecord);

        when(noticeRecordMapper.findByNoticeType(typeId)).thenReturn(noticeRecords);

        // When
        List<NoticeRecord> result = noticeRecordService.findByNoticeType(typeId);

        // Then
        assertThat(result).isEqualTo(noticeRecords);
        verify(noticeRecordMapper).findByNoticeType(typeId);
    }

}
