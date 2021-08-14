package com.example.kampung_unite_web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    @OneToMany(mappedBy = "userDetail")
    private List<GroceryList> grocerylists;


    public UserDetail(String username, String password, int id, String firstName, String lastName, String role, String phoneNumber, String homeAddress) {
        super(username,password);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
    }
}
