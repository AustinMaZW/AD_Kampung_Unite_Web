package com.example.kampung_unite_web;

import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class KampungUniteWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(KampungUniteWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserDetailRepository udrepo){
        return args -> {
            List<UserDetail> userDetailList = new ArrayList<>();
            userDetailList.add(new UserDetail("austin", "austin", 1,"Austin", "Ma",null, "Austin HP", "Austin Address"));
            userDetailList.add(new UserDetail("ck", "ck", 2,"CK", "Tang",null, "CK HP", "CK Address"));
            userDetailList.add(new UserDetail("jean", "jean", 3, "YJ", "Lim", null, "Jean HP", "Jean Address"));
            userDetailList.add(new UserDetail("ngu", "ngu", 4, "Ngu", "Zaw", null, "Ngu HP", "Ngu Address"));
            userDetailList.add(new UserDetail("yue", "yue", 5, "PC", "Yue", null, "Yue HP", "Yue Address"));
            userDetailList.add(new UserDetail("yz", "yz", 6, "YZ", "Chua", null, "YZ HP", "YZ Address"));
            udrepo.saveAll(userDetailList);
        };
    }

}
