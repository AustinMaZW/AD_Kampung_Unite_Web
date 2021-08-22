package com.example.kampung_unite_web.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kampung_unite_web.Interfaces.UserDetailService;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.UserLogin;
import com.example.kampung_unite_web.repo.UserDetailRepository;

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

}
