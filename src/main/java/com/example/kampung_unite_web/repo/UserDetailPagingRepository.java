package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.UserDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserDetailPagingRepository extends PagingAndSortingRepository<UserDetail, Integer> {
    @Query("SELECT u from UserDetail u WHERE u.firstName LIKE %:name% or u.lastName LIKE %:name%")
    public Page<UserDetail> searchUsersByNamePage(@Param("name") String name, Pageable pageable);
}
