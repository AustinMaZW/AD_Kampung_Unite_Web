package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.service.UserDetailService;
import com.example.kampung_unite_web.model.UserDetail;
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
    public List<UserDetail> getListUserDetail() {
        List<UserDetail> userDetailList = udService.findAllUsers();
        return userDetailList;
    }

    @RequestMapping("find/{id}")
    public UserDetail findUserById(@PathVariable("id") int id) {
        UserDetail ud = udService.findUserById(id);
        System.out.println(ud);
        return ud;
    }

    // create put on hold for now
    @PostMapping("create")
    public UserDetail createUserDetail(@RequestBody UserDetail userDetail){
        boolean isCreated=false;
        UserDetail ud = new UserDetail();
        boolean isValidUD = valUserDetailInfo(userDetail);
        if(isValidUD){
            isCreated = udService.createUser(userDetail);
        }
        else
            System.out.println("userDetail = null");

        if(isCreated){
            ud = udService.findUserByUsername(userDetail.getUsername());
        }

        return ud;
    }

    private boolean valUserDetailInfo(UserDetail userDetail) {
        boolean isComplete=true;
        if(userDetail.getUsername()!=""){}
        else{
            isComplete = false;
            System.out.println("username = null");
        }
        if(userDetail.getPassword()!=""){}
        else{
            isComplete = false;
            System.out.println("password = null");
        }
        if (userDetail.getFirstName() !=""){}
        else{
            isComplete= false;
            System.out.println("firstname = null");
        }
        if(userDetail.getLastName()!=""){}
        else{
            isComplete=false;
            System.out.println("lastname = null");
        }
        if(userDetail.getPhoneNumber()!=""){}
        else{
            isComplete=false;
            System.out.println("phonenumber = null");
        }
        if(userDetail.getHomeAddress()!=""){}
        else{
            System.out.println("address = null");
        }
        return isComplete;
    }

    @PutMapping("update")
    public void updateUserDetail(@RequestBody UserDetail userDetail) {
        if (userDetail != null) {
            udService.updateUser(userDetail);
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteUserDetail(@PathVariable("id") int id){
//        System.out.println(udService.findUserById(id)); //test out code only
        udService.deleteUserById(id);
    }

//    Uses json in requestbody to pass
    @PostMapping("login")
    public UserDetail login(@RequestBody UserDetail userDetail) {
        String authentication = null;
        int userId = 0;
        String udUsername = userDetail.getUsername();
        UserDetail repoUD= udService.findUserByUsername(udUsername);
        if (userDetail.getAuthentication()==null || !userDetail.getAuthentication().matches(repoUD.getAuthentication())){
            if(userDetail.getPassword().matches(repoUD.getPassword())){
                userId = repoUD.getId();
                userDetail.setId(userId);
                if (repoUD.getAuthentication() != null){
                    authentication = repoUD.getAuthentication();
                }
                else{
                    authentication = PasswordEncoder().encode(userDetail.getPassword());
                    repoUD.setAuthentication(authentication);
                    udService.updateUser(repoUD);
                }
                userDetail.setAuthentication(authentication);
            }
        }
        return userDetail;
    }

//    Use url to login (cons: password cannot contain slashes)
//    @GetMapping("login/{username}&&{password}") // returns an Authentication String
//    public String login(@PathVariable("username") String username, @PathVariable("password") String password){
//        String authentication = null;
//        UserDetail userDetail = udService.findUserByUsername(username); //find user
//
//        System.out.println(userDetail.getPassword().toString()); //test purpose only
//
//        if (password.matches(userDetail.getPassword().toString())){
//            if (userDetail.getAuthentication() != null) {
//                authentication = userDetail.getAuthentication();
//            }
//            else {
//                authentication = PasswordEncoder().encode(password);
//                userDetail.setAuthentication(authentication);
//                udService.updateUser(userDetail);
//            }
//        }
//        System.out.println(authentication);//test purpose only
//        return authentication;
//    }

    private PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("logout/_un={username}")
    public String logout(@PathVariable("username") String username) {
        UserDetail userDetail = udService.findUserByUsername(username);
        if (userDetail.getAuthentication() != null) {
            udService.logoutUser(userDetail);
        }
        return "logout success";
    }

    @GetMapping("logout/_id={id}")
    public String logout(@PathVariable("id") int id) {
        UserDetail userDetail = udService.findUserById(id);
        if (userDetail.getAuthentication() != null) {
            udService.logoutUser(userDetail);
        }
        return "logout success";
    }

    @GetMapping("authenticate/{username}")
    public String authenticateLogin(@PathVariable("username") String username) {
        String auth = null;
        UserDetail ud = udService.findUserByUsername(username);
        if (ud.getAuthentication() != null) {
            auth = ud.getAuthentication();
        }
        return auth;
    }


    //incomplete... might not use it at all
    @PostMapping("authenticate")
    public UserDetail authenticateLogin(@RequestBody UserDetail userDetail) {
        String auth = null;
        UserDetail ud = udService.findUserByUsername(userDetail.getUsername());
        if (ud.getAuthentication() != null) {
            auth = ud.getAuthentication();
        }
        return userDetail;
    }

}
