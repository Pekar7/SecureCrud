package com.example.crud.service;

import com.example.crud.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo findUserInfoById(Long id) {
        return userInfoRepository.findById(id);
    }

    @Override
    public List<UserInfo> findAllUserInfo() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo addUserInfo(UserInfo existingUser) {
        userInfoRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUserInfo(UserInfo user) {
        userInfoRepository.delete(user);
    }
}
