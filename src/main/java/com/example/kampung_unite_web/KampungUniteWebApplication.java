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

    @Autowired
    UserDetailRepository udrepo;

    public static void main(String[] args) {
        SpringApplication.run(KampungUniteWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
            List<UserDetail> userDetailList = new ArrayList<>();
            userDetailList.add(new UserDetail(1,"austin", "austin", "Austin", "Ma",null, "Austin HP", "Austin Address"));
            userDetailList.add(new UserDetail(2,"ck", "ck", "CK", "Tang",null, "CK HP", "CK Address"));
            userDetailList.add(new UserDetail(3, "jean", "jean",  "YJ", "Lim", null, "Jean HP", "Jean Address"));
            userDetailList.add(new UserDetail(4,"ngu", "ngu",  "Ngu", "Zaw", null, "Ngu HP", "Ngu Address"));
            userDetailList.add(new UserDetail(5,"yue", "yue",  "PC", "Yue", null, "Yue HP", "Yue Address"));
            userDetailList.add(new UserDetail(6,"yz", "yz",  "YZ", "Chua", null, "YZ HP", "YZ Address"));
            udrepo.saveAll(userDetailList);
        };
    }

}
