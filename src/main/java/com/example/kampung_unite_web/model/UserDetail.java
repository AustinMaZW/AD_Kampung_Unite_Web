package com.example.kampung_unite_web.model;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class UserDetail extends UserLogin{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private String phoneNumber;
    private String homeAddress;
    private String authentication;

    @OneToMany(mappedBy = "userDetail")
    private List<GroceryList> grocerylists;

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public void generateAuthentication() {
        String password = super.getPassword();
        this.authentication = authenticationToken(password);
    }

    public String authenticationToken(String password) {
        String encodedPassword = PasswordEncoder().encode(password);
        return encodedPassword;
    }

    private PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDetail(int id, String username, String password,  String firstName, String lastName, String role, String phoneNumber, String homeAddress) {
        super(username,password);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
    }

    public UserDetail(String username, String password, String firstName, String lastName, String role, String phoneNumber, String homeAddress) {
        super(username,password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
    }

    public UserDetail(String username, String password, String firstName, String lastName) {
        super(username,password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDetail(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
