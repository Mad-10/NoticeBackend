package com.zhbit.noticebackend.service;

import com.zhbit.noticebackend.entity.User;
import com.zhbit.noticebackend.exception.PasswordNotMatchException;
import com.zhbit.noticebackend.exception.UserAlreadyExistsException;
import com.zhbit.noticebackend.exception.UserNotFoundException;
import com.zhbit.noticebackend.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

class UserServiceTest {
    @Test
    void testRegisterUserAlreadyExistsException() {
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        UserService userService = new UserService(userMapper);
        User user = new User();
        user.setUsername("test");
        when(userMapper.getByUsername("test")).thenReturn(user);

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.register(user));
    }

    @Test
    void testRegisterSuccess() throws UserAlreadyExistsException {
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        UserService userService = new UserService(userMapper);
        User user = new User();
        user.setUsername("test");
        when(userMapper.getByUsername("test")).thenReturn(null);
        when(userMapper.save(user)).thenReturn(1);

        boolean result = userService.register(user);

        Assertions.assertTrue(result);
    }

    @Test
    void testLoginUserNotFoundException() {
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        UserService userService = new UserService(userMapper);
        when(userMapper.getByUsername("test")).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.login("test", "test"));
    }

    @Test
    void testLoginPasswordNotMatchException() {
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        UserService userService = new UserService(userMapper);
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        when(userMapper.getByUsername("test")).thenReturn(user);

        Assertions.assertThrows(PasswordNotMatchException.class, () -> userService.login("test", "123456"));
    }

    @Test
    void testLoginSuccess() throws UserNotFoundException, PasswordNotMatchException {
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        UserService userService = new UserService(userMapper);
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        when(userMapper.getByUsername("test")).thenReturn(user);

        User result = userService.login("test", "test");

        Assertions.assertEquals(result.getUsername(), "test");
        Assertions.assertEquals(result.getPassword(), "test");
    }

}