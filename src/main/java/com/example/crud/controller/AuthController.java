package com.example.crud.controller;

import com.example.crud.dto.RegisterDto;
import com.example.crud.model.Role;
import com.example.crud.model.UserInfo;
import com.example.crud.service.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userInfoRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Пользователь " + registerDto.getUsername() + " уже существует", HttpStatus.BAD_REQUEST);
        }

        UserInfo user = new UserInfo();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setRoles(Role.USER);

        userInfoRepository.save(user);

        return new ResponseEntity<>("Успешная регестрация нового пользователя " + registerDto.getUsername(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public Object login(@RequestBody UserInfo loginDto) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<UserInfo> savedUser = userInfoRepository.findUserInfoByUsername(loginDto.getUsername());

        if (savedUser.isPresent()) {
            if (passwordEncoder.matches(loginDto.getPassword(), savedUser.get().getPassword()) && loginDto.getUsername().equals(savedUser.get().getUsername())) {
                return savedUser.get();
            } else if (!(passwordEncoder.matches(loginDto.getPassword(), savedUser.get().getPassword())) && loginDto.getUsername().equals(savedUser.get().getUsername())){
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный логин/пароль");
            }
        }

        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не существует");

    }
}

