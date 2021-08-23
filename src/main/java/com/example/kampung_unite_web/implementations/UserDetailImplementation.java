package com.example.kampung_unite_web.implementations;

import com.example.kampung_unite_web.service.UserDetailService;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.repo.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailImplementation implements UserDetailService {

    @Autowired
    UserDetailRepository udrepo;

    @Override
    public UserDetail findUserById(int id) {
        UserDetail ud = null;
        if(udrepo.findById(id).isPresent()){
            ud = udrepo.findById(id).get();
        }
        return ud;
    }

    @Override
    public List<UserDetail> findAllUsers() {
        return udrepo.findAll();
    }

    @Override
    public boolean createUser(UserDetail userDetail) {
        String newUsername =  userDetail.getUsername();
        boolean isValidNewUsername = false;
        int usernameExisted = 0;
        List<UserDetail> listUsers = udrepo.findAll();
        for(UserDetail user : listUsers){
            if(user.getUsername().matches(newUsername)){
                usernameExisted ++;
            }
        }
        if(usernameExisted == 0){
            isValidNewUsername = true;
            udrepo.save(userDetail);
        }
        return isValidNewUsername;
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
