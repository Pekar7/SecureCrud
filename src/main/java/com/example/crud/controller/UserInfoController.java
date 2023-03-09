package com.example.crud.controller;

import com.example.crud.model.UserInfo;
import com.example.crud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInfoController(UserInfoService userInfoService, PasswordEncoder passwordEncoder) {
        this.userInfoService = userInfoService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping()
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        List<UserInfo> users = userInfoService.findAllUserInfo();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserInfo user = userInfoService.findUserInfoById(id);
        if (user == null) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserInfo user) {
        UserInfo savedUser = userInfoService.addUserInfo(user);
        if (savedUser == null) {
            return new ResponseEntity<>("Не получилсоь сохранить пользователя", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserInfo user) {
        UserInfo existingUser = userInfoService.findUserInfoById(id);
        if (existingUser == null) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
        } else {
            existingUser.setUsername(user.getUsername());
            existingUser.setRoles(user.getRoles());
            existingUser.setPassword(passwordEncoder.encode((user.getPassword())));
            userInfoService.addUserInfo(existingUser);
            return new ResponseEntity<>("Данные пользователя успешно добавлены", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        UserInfo user = userInfoService.findUserInfoById(id);
        if (user == null) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
        } else {
            userInfoService.deleteUserInfo(user);
            return new ResponseEntity<>("Пользователь успешно удален", HttpStatus.OK);
        }
    }
}
