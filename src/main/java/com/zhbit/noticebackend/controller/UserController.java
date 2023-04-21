package com.zhbit.noticebackend.controller;

import com.zhbit.noticebackend.entity.User;
import com.zhbit.noticebackend.entity.UserDto;
import com.zhbit.noticebackend.exception.user.*;
import com.zhbit.noticebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                throw new UsernameEmptyException();
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new PasswordEmptyException();
            }
            boolean success = userService.register(user);
            if (success) {
                return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
            } else {
                throw new UnknownRegisterException();
            }
        } catch (UserAlreadyExistsException | UsernameEmptyException | PasswordEmptyException | UnknownRegisterException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/greeting")
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Hello!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody User user) {
        UserDto userDto = new UserDto();
        User loginUser;
        try {
            if (user.getUsername() == null ||  user.getUsername().isEmpty()) {
                throw new UsernameEmptyException();
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new PasswordEmptyException();
            }
            loginUser = userService.login(user.getUsername(), user.getPassword());
            if (loginUser != null) {
                userDto.setId(loginUser.getId());
                userDto.setUsername(loginUser.getUsername());
                return ResponseEntity.ok(userDto);
            }

        } catch (UserNotFoundException | PasswordNotMatchException | UsernameEmptyException | PasswordEmptyException e) {
            userDto.setMessage(e.getMessage());
        }
        return ResponseEntity.badRequest().body(userDto);
    }
}
