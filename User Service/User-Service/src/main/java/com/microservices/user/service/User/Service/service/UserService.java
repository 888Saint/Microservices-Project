package com.microservices.user.service.User.Service.service;


import com.microservices.user.service.User.Service.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

    User updateUser(String userId, User newUser);

    String deleteUser(String userId);
}
