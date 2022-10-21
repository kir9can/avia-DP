package com.example.avia.service;

import java.util.List;

import com.example.avia.dto.LoginDto;



public interface LoginService {
    public List<String> loginUser(LoginDto loginDto);
}
