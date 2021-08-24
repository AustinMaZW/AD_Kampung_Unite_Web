package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.UserLogin;

import java.util.List;

public interface UserDetailService {

    UserDetail findUserById(int id);

    List<UserDetail> findAllUsers();

    boolean createUser(UserDetail userDetail);

    void deleteUserById(int id);

    UserDetail findUserByUsername(String username);

    void logoutUser(UserDetail userDetail);

    void updateUser(UserDetail userDetail);

    public UserDetail findUserByUsernameAndPassword(String username, String password);

    public boolean authenticateUser(UserLogin ud);
}
