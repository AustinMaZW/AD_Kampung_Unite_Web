package com.example.kampung_unite_web.implementations;

import com.example.kampung_unite_web.Interfaces.UserDetailService;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailImplementation implements UserDetailService {

    @Autowired
    UserDetailRepository udrepo;

    @Override
    public UserDetail findUserById(int id) {
        return udrepo.findById(id).get();
    }

    @Override
    public List<UserDetail> findAllUsers() {
        return udrepo.findAll();
    }

    @Override
    public void createUser(UserDetail userDetail) {
        udrepo.save(userDetail);
    }

    @Override
    public void deleteUserById(int id) {
        udrepo.deleteById(id);
    }

    @Override
    public UserDetail findUserByUsername(String username) {
        return udrepo.findByUsername(username);
    }

    @Override
    public void logoutUser(UserDetail userDetail) {
        userDetail.setAuthentication(null);
        udrepo.save(userDetail);
    }

    @Override
    public void updateUser(UserDetail userDetail) {
        udrepo.save(userDetail);
    }

}
