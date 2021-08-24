package com.example.kampung_unite_web.implementations;

import com.example.kampung_unite_web.service.UserDetailService;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.UserLogin;
import com.example.kampung_unite_web.repo.UserDetailPagingRepository;
import com.example.kampung_unite_web.repo.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class UserDetailImplementation implements UserDetailService {

    @Autowired
    UserDetailRepository udrepo;

    @Autowired
    UserDetailPagingRepository udprepo;

    @Override
    public UserDetail findUserById(int id) {
        UserDetail ud = null;
        if (udrepo.findById(id).isPresent()) {
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
        String newUsername = userDetail.getUsername();
        boolean isValidNewUsername = false;
        int usernameExisted = 0;
        List<UserDetail> listUsers = udrepo.findAll();
        for (UserDetail user : listUsers) {
            if (user.getUsername().matches(newUsername)) {
                usernameExisted++;
            }
        }
        if (usernameExisted == 0) {
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

    @Override
    public UserDetail findUserByUsernameAndPassword(String username, String password) {
        UserDetail usr = udrepo.findUserByusernameAndPassword(username, password);
        return usr;
    }

    @Override
    public boolean authenticateUser(UserLogin ud) {
        UserDetail usr = udrepo.findUserByusernameAndPassword(ud.getUsername(), ud.getPassword());
        if (ud.getUsername().equals(usr.getUsername()) && ud.getPassword().equals(usr.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public Page<UserDetail> getAllUserByPage(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, 10, Sort.by("id").ascending()); // set default num of items to 10 here
                                                                                 // and sort by id
        return udprepo.findAll(paging);
    }

}
