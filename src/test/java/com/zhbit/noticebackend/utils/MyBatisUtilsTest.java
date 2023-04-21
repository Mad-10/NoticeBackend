package com.zhbit.noticebackend.utils;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MyBatisUtilsTest {

    @Test
    void getSqlSession() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        System.out.println(sqlSession == null?"Failed!":"Success");
    }
}