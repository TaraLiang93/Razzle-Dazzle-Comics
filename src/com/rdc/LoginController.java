package com.rdc;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class LoginController {


    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView homepage(HttpSession session, ModelMap map){

        session.setAttribute("logInURL",LOGIN);

        return new ModelAndView("homepage");
    }


    @RequestMapping(value=LOGIN, method= RequestMethod.GET)
    public String login(HttpSession session,HttpServletRequest req, ModelMap map){

        UserService userService = UserServiceFactory.getUserService();


        if(session.getAttribute("logOutURL") != null)//checks to see if the logutUrl has been generated
            session.removeAttribute("logOutURL");

        session.setAttribute("logInURL",req.getHeader("Referer"));
        System.out.println(req.getHeader("Referer"));

//        return new ModelAndView("test");
        return "redirect:" + userService.createLoginURL(req.getHeader("Referer"));

    }




}
