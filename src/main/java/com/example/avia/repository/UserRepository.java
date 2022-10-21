package com.example.avia.repository;

import com.example.avia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUsernameAnfPassword(String username,String password);
}
