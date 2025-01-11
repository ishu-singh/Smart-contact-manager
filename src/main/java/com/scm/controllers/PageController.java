package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;

@Controller
public class PageController {


    @RequestMapping("/home")
    public String home(Model m){
        System.out.println("going to home page");
        m.addAttribute("name", "ishu");
        return "home";
    }

@RequestMapping("/about")
    public String aboutPage(){
        System.out.println("this is my about page");
        return "about";
    }


    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("this is my service page");
        return "service";
    }

    @RequestMapping("/contact")
    public String contact(){
        System.out.println("this is my contact page");
        return "contact";
    }
    @RequestMapping("/login")
    public String login(){
        System.out.println("this is my login page");
        return "login";
    }
    @RequestMapping("/register")
    public String register(Model model){
        System.out.println("this is my register page");
        UserForm userForm=new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }
    //processing registration form
    @RequestMapping(value="/do-register",method = RequestMethod.POST)
    public String registerUser(@ModelAttribute UserForm userForm){
        System.out.println("this is my register page");
        //fetch the data
        System.out.println(userForm);

        //validate the data
        //save to database
        //message-successfull
        //redirect to login page

        return "redirect:/register";

    }
    
}
