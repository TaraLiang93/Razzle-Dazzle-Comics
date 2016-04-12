package com.rdc;

import com.data.Globals;
import com.data.UserData;
import com.data.api.createables.UserDataCreater;
import com.data.api.createables.fillCommands.UserDataFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.Createable;
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

        if(session.getAttribute("user") == null && session.getAttribute("loginURL") == null)
            session.setAttribute("loginURL",LOGIN);

        Globals globals = (Globals) session.getAttribute("globals");
        if(globals == null)
        {
            globals = new Globals();
            session.setAttribute("globals",globals);
        }

        return new ModelAndView("homepage");
    }


    @RequestMapping(value =LOGIN, method= RequestMethod.GET)
    public String login(@RequestHeader("Referer") String ref, HttpSession session, ModelMap map){

        UserService userService = UserServiceFactory.getUserService();

            session.setAttribute("loginURL",null);

        session.setAttribute("logoutURL", LOGOUT);

        return "redirect:" + userService.createLoginURL("/setUser"+"?ref=" + ref);

    }

    @RequestMapping(value ="/setUser", method= RequestMethod.GET)
    public String setUserName(@RequestParam String ref,HttpSession session)
    {
        UserService userService = UserServiceFactory.getUserService();

        Createable<UserData> userDataCreateable = new UserDataCreater(userService.getCurrentUser());
        try {
            UserData userData = userDataCreateable.createEntity(new UserDataFillCommand());
            session.setAttribute("user",userData);
        } catch (CreateException e) {
            e.printStackTrace();
        }





        return "redirect:"+ ref;
    }

    @RequestMapping(value =LOGOUT, method= RequestMethod.GET)
    public String logout(HttpSession session, ModelMap map){

        UserService userService = UserServiceFactory.getUserService();

        if(session.getAttribute("logoutURL") != null)
            session.removeAttribute("logoutURL");

        session.removeAttribute("user");


        return "redirect:" + userService.createLogoutURL("/");

    }




}
