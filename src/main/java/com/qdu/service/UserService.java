package com.qdu.service;

import com.qdu.pojo.User;

public interface UserService {
    User login(User user);
    int add(User user);
}
