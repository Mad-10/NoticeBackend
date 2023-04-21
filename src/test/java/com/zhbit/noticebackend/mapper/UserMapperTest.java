package com.zhbit.noticebackend.mapper;

import com.zhbit.noticebackend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("123456");
        int result = userMapper.save(user);
        assertEquals(1, result);
        assertNotNull(user.getId());
    }

    @Test
    public void testDeleteById() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("123456");
        userMapper.save(user);
        int result = userMapper.deleteById(user.getId());
        assertEquals(1, result);
        assertNull(userMapper.getById(user.getId()));
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("123456");
        userMapper.save(user);
        user.setUsername("newTestUser");
        user.setPassword("654321");
        int result = userMapper.update(user);
        assertEquals(1, result);
        User updatedUser = userMapper.getById(user.getId());
        assertEquals("newTestUser", updatedUser.getUsername());
        assertEquals("654321", updatedUser.getPassword());
    }

    @Test
    public void testGetById() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("123456");
        userMapper.save(user);
        User queriedUser = userMapper.getById(user.getId());
        assertEquals(user.getId(), queriedUser.getId());
        assertEquals(user.getUsername(), queriedUser.getUsername());
        assertEquals(user.getPassword(), queriedUser.getPassword());
    }

    @Test
    public void testGetByUsername() {
        User user = new User();
        user.setUsername("TestUser000");
        user.setPassword("123456");
        userMapper.save(user);
        User queriedUser = userMapper.getByUsername(user.getUsername());
        assertEquals(user.getId(), queriedUser.getId());
        assertEquals(user.getUsername(), queriedUser.getUsername());
        assertEquals(user.getPassword(), queriedUser.getPassword());
    }

    @Test
    public void testListAll() {
        User user1 = new User();
        user1.setUsername("testUser1");
        user1.setPassword("123456");
        userMapper.save(user1);
        User user2 = new User();
        user2.setUsername("testUser2");
        user2.setPassword("654321");
        userMapper.save(user2);
        List<User> userList = userMapper.listAll();
        assertEquals(2, userList.size());
    }

    @Test
    public void testGetByCondition() {
        User user1 = new User();
        user1.setUsername("testUser1");
        user1.setPassword("123456");
        userMapper.save(user1);
        User user2 = new User();
        user2.setUsername("testUser2");
        user2.setPassword("654321");
        userMapper.save(user2);
        User queryUser = new User();
        queryUser.setUsername("testUser1");
        List<User> userList = userMapper.getByCondition(queryUser, null, null);
        assertEquals(1, userList.size());
        assertEquals(user1.getId(), userList.get(0).getId());
        assertEquals(user1.getUsername(), userList.get(0).getUsername());
        assertEquals(user1.getPassword(), userList.get(0).getPassword());
    }

}
