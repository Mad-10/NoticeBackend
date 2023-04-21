package com.zhbit.noticebackend.service;

import com.zhbit.noticebackend.entity.User;
import com.zhbit.noticebackend.exception.user.PasswordNotMatchException;
import com.zhbit.noticebackend.exception.user.UserAlreadyExistsException;
import com.zhbit.noticebackend.exception.user.UserNotFoundException;
import com.zhbit.noticebackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService {


    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean checkUserExists(String username) {
        User user = userMapper.getByUsername(username);
        return user != null;
    }

    public boolean register(User user) throws UserAlreadyExistsException {
        if (checkUserExists(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        return userMapper.save(user) > 0;
    }

    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        User user = userMapper.getByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User has not been registered.");
        }
        if (!user.getPassword().equals(password)) {
            throw new PasswordNotMatchException();
        }
        return user;
    }

}
