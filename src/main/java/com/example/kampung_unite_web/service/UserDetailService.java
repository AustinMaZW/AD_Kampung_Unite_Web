package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.UserLogin;

import org.springframework.data.domain.Page;

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

    public Page<UserDetail> getAllUserByPage(int pageNo);

    public Page<UserDetail> getUsersByPage(int pageNo, String name);

    public List<UserDetail> searchUsersByName(String name);
}
