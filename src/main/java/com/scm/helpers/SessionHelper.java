package com.scm.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {
    public static void removeMessage() {
        // method to remove the message alert on the sign up page
        try{

        System.out.println("Removing message from session");
        HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("message");

    } catch (Exception e) {
        System.out.println("some error occured:" + e);
        e.printStackTrace();
    }
    

}
}