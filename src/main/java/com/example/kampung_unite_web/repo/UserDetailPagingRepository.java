package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.UserDetail;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDetailPagingRepository extends PagingAndSortingRepository<UserDetail, Integer> {

}
