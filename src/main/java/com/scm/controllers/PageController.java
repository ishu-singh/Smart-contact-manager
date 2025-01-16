package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }


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
    @GetMapping("/login")
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
    public String registerUser(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult, HttpSession session){
        System.out.println("this is my register page");
        //fetch the data
        System.out.println(userForm);

        //validate the data
        if(rBindingResult.hasErrors()){
            System.out.println("error "+rBindingResult);
            return "register";
        }


        //save to database
        // User user=User.builder()
        // .userName(userForm.getName())
        // .userEmail(userForm.getEmail())
        // .userPassword(userForm.getPassword())
        // .phoneNumber(userForm.getPhoneNumber())
        // .about(userForm.getAbout())
        // .build();

        User user=new User();
        user.setUserName(userForm.getName());
        user.setUserEmail(userForm.getEmail()); 
        user.setUserPassword(userForm.getPassword());   
        user.setPhoneNumber(userForm.getPhoneNumber()); 
        user.setAbout(user.getAbout());
       User savedUser= userService.saveUser(user);
       System.out.println("user saved with id "+savedUser.getUserId());
        
        //message-successfull, we can use session
       Message message= Message.builder().content("Registration Successfull").type(MessageType.green).build();
        session.setAttribute("message", message);



        //redirect to login page

        return "redirect:/register";

    }
    
}
