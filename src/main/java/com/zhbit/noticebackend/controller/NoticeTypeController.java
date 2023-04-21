package com.zhbit.noticebackend.controller;

import com.zhbit.noticebackend.entity.NoticeType;
import com.zhbit.noticebackend.entity.NoticeTypeDto;
import com.zhbit.noticebackend.exception.notice_type.NoticeTypeNotFoundException;
import com.zhbit.noticebackend.service.NoticeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice_type")
public class NoticeTypeController {
    private final NoticeTypeService noticeTypeService;

    @Autowired
    public NoticeTypeController(NoticeTypeService noticeTypeService) {
        this.noticeTypeService = noticeTypeService;
    }

    @PostMapping("/get_all_notice_type")
    public ResponseEntity<NoticeTypeDto> getAllNoticeTypes() {
        NoticeTypeDto noticeTypeDto = new NoticeTypeDto();
        try {
            List<NoticeType> result = noticeTypeService.findAll();
            if (result != null && result.size() != 0) {
                noticeTypeDto.setNoticeTypes(result);
                return ResponseEntity.ok(noticeTypeDto);
            } else {
                throw new NoticeTypeNotFoundException();
            }
        } catch (NoticeTypeNotFoundException e) {
            noticeTypeDto.setMessage(e.getMessage());
        }
        return ResponseEntity.badRequest().body(noticeTypeDto);
    }
}
