package com.example.avia.service;

import com.example.avia.dto.AviaPermissionDto;
import com.example.avia.dto.ResponseData;
import com.example.avia.entity.User;

public interface AviaService {
     ResponseData grantAviaPermissions(AviaPermissionDto permission) ;

    public User getAllPassengerDetails(int userId);
}
