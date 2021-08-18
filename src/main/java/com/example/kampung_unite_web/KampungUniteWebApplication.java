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


}
