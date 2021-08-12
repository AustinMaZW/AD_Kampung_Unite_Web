package com.example.kampung_unite_web.model;

import lombok.*;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class UserLogin {
    private String username;
    private String password;

}
