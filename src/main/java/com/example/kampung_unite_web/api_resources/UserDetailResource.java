package com.example.kampung_unite_web.api_resources;

import com.example.kampung_unite_web.Interfaces.UserDetailService;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserDetailResource {

    @Autowired
    UserDetailService udService;

    @GetMapping("find/all")
    public List<UserDetail> getListUserDetail(){
        List<UserDetail> userDetailList = udService.findAllUsers();
        return userDetailList;
    }

    @RequestMapping("find/{id}")
    public UserDetail findUserById(@PathVariable("id") int id){
        UserDetail ud = udService.findUserById(id);
        System.out.println(ud);
        return ud;
    }


    //create put on hold for now
    @PostMapping("create")
    public UserDetail createUserDetail(@RequestBody UserDetail userDetail){
        if(userDetail != null){
            udService.createUser(userDetail);
        }
        else
            System.out.println("userDetail = null");
        return userDetail;
    }

    //update put on hold for now
    @PutMapping("update")
    public void updateUserDetail(@RequestBody UserDetail userDetail){
        if (userDetail!= null) {
            udService.updateUser(userDetail);
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteUserDetail(@PathVariable("id") int id){
        System.out.println(udService.findUserById(id)); //test out code only
        udService.deleteUserById(id);
    }

    @GetMapping("login/{username}&&{password}") // returns an Authentication String
    public String login(@PathVariable("username") String username, @PathVariable("password") String password){
        String authentication = null;
        UserDetail userDetail = udService.findUserByUsername(username); //find user

        System.out.println(userDetail.getPassword().toString()); //test purpose only

        if (password.matches(userDetail.getPassword().toString())){
            if (userDetail.getAuthentication() != null) {
                authentication = userDetail.getAuthentication();
            }
            else {
                authentication = PasswordEncoder().encode(password);
                userDetail.setAuthentication(authentication);
                udService.updateUser(userDetail);
            }
        }

        System.out.println(authentication);//test purpose only

        return authentication;
    }

    private PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("logout/{username}")
    public String logout(@PathVariable("username") String username){
        UserDetail userDetail = udService.findUserByUsername(username);
        if (userDetail.getAuthentication()!=null){
            udService.logoutUser(userDetail);
        }
        return "logout success";
    }

    @GetMapping("authenticate")
    public String authenticateLogin(@RequestBody UserDetail userDetail){
        String auth = null;
        UserDetail ud = udService.findUserByUsername(userDetail.getUsername());
        if (userDetail.getAuthentication().matches(ud.getAuthentication())){
            auth = userDetail.getAuthentication();
        }
        System.out.println(auth);
        return auth;
    }

}
