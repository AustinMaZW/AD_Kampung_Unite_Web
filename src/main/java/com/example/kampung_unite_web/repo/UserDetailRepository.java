package com.example.kampung_unite_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kampung_unite_web.model.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

	UserDetail findByUsername(String username);

	@Query("select u from UserDetail u where u.username = :username and u.password = :password")
	public UserDetail findUserByusernameAndPassword(@Param("username") String username,
			@Param("password") String password);
}
