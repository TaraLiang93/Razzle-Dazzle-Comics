package com.rdc;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class LoginController {


    @RequestMapping(value ="/login", method= RequestMethod.GET)
    public String loadHomePage(HttpServletRequest req, ModelMap map){

        UserService userService = UserServiceFactory.getUserService();

        HttpSession session = req.getSession();


        if(session.getAttribute("logOutURL") != null)//checks to see if the logutUrl has been generated
            session.removeAttribute("logOutURL");

        session.setAttribute("logInURL",req.getHeader("Referer"));
        System.out.println(req.getHeader("Referer"));

        return "redirect:" + userService.createLoginURL(req.getHeader("Referer"));

    }


}
