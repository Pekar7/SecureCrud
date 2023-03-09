package com.example.crud.service;

import com.example.crud.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo findUserInfoById(Long id);
    List<UserInfo> findAllUserInfo();
    UserInfo addUserInfo(UserInfo existingUser);
    void deleteUserInfo(UserInfo user);
}
