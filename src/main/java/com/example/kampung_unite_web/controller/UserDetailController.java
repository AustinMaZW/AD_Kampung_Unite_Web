package com.example.kampung_unite_web.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.service.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/userdetail")
public class UserDetailController {
    private static final String USER_LIST = "userlist";
    private static final String UPDATE_USER_FORM = "update_user";
    @Autowired
    private UserDetailService us;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String listUsers(Model model) {
        return listUsersByPage(1, model); // default is page 1 (index 0)
    }

    @RequestMapping(path = "/{pageNo}", method = RequestMethod.GET)
    public String listUsersByPage(@PathVariable("pageNo") Integer pageNo, Model model) {

        Page<UserDetail> pageResult = us.getAllUserByPage(pageNo - 1); // -1 here because it's 0 base index
                                                                       // in repo
        long totalItems = pageResult.getTotalElements(); // get total number of products in database
        int totalPages = pageResult.getTotalPages(); // get total pages
        int currentNumItems = pageResult.getNumberOfElements();

        List<UserDetail> uList = new ArrayList<>();
        if (pageResult.hasContent()) {
            uList = pageResult.getContent();
        }

        model.addAttribute("userlist", uList);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentNumItems", currentNumItems);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("page", true);
        return USER_LIST;
    }

    @RequestMapping("/edit/{userId}")
    public String editUser(@PathVariable("userId") int id, Model model) {
        UserDetail user = us.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("text", "Edit User");
        return UPDATE_USER_FORM;
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int id, Model model) {
        UserDetail user = us.findUserById(id);
        us.deleteUserById(user.getId());
        return "redirect:/userdetail/list";
    }
}
