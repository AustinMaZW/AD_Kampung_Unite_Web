package com.example.kampung_unite_web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kampung_unite_web.Interfaces.UserDetailService;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.UserLogin;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserDetailService us;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		UserLogin ul = new UserLogin();
		model.addAttribute("user", ul);
		return "login";
	}

	@RequestMapping(path = "/authenticatie", method = RequestMethod.POST)
	public String autenticate(@ModelAttribute("user") UserLogin ud, Model model, HttpSession session) {
		if (us.authenticateUser(ud)) {
			UserDetail u = us.findUserByUsernameAndPassword(ud.getUsername(), ud.getPassword());
			UserLogin ul = new UserLogin();
			ul.setUsername(u.getUsername());
			ul.setPassword(u.getPassword());
			session.setAttribute("userSession", u);
			System.out.println(u.getUsername());
			return "welcome";
		} else {
			return "login";
		}
	}
}
