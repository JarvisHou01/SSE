package com.qdu.service;


import com.qdu.dao.UserMapper;
import com.qdu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public User login(User user) {
        // TODO Auto-generated method stub
        return userMapper.login(user);
    }


    public int add(User user) {
        // TODO Auto-generated method stub
        return userMapper.add(user);
    }

}
