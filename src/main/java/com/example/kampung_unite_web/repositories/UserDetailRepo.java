package com.example.kampung_unite_web.repositories;

import com.example.kampung_unite_web.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDetailRepo extends JpaRepository<UserDetail, Integer> {

    UserDetail findByUsername(String username);
}
