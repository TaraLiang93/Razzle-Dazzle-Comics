package com.rdc;

import com.data.Globals;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

        if(session.getAttribute("loginURL") == null)
            session.setAttribute("loginURL",LOGIN);

        Globals globals = (Globals) session.getAttribute("globals");

        return new ModelAndView("homepage");
    }


    @RequestMapping(value =LOGIN, method= RequestMethod.GET)
    public String login(@RequestHeader("Referer") String ref, HttpSession session, ModelMap map){

        UserService userService = UserServiceFactory.getUserService();

            session.setAttribute("loginURL",null);

        session.setAttribute("logoutURL", LOGOUT);
//        session.setAttribute("nickname",userService.getCurrentUser().getNickname());

        return "redirect:" + userService.createLoginURL("/setUser"+"?ref=" + ref);

    }

    @RequestMapping(value ="/setUser", method= RequestMethod.GET)
    public ModelAndView setUserName(@RequestParam String ref,HttpSession session)
    {
        String nickname = UserServiceFactory.getUserService().getCurrentUser().getNickname();

        session.setAttribute("nickname",nickname);

        return new ModelAndView("homepage");
    }

    @RequestMapping(value =LOGOUT, method= RequestMethod.GET)
    public String logout(HttpSession session, ModelMap map){

        UserService userService = UserServiceFactory.getUserService();

        if(session.getAttribute("logoutURL") != null)
            session.removeAttribute("logoutURL");


        return "redirect:" + userService.createLogoutURL("/");

    }




}
