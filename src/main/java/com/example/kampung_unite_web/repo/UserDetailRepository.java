package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    UserDetail findByUsername(String username);

}
