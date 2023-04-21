package com.zhbit.noticebackend.entity;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String message;

}
