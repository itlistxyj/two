package com.czxy.service;

import com.czxy.domain.User;

public interface UserService {
    User findUserByNameAndPassword(User user);
}
