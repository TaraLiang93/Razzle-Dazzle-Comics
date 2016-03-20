package com.rdc;

import com.data.user;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by drodrigues on 3/19/16.
 */
@Controller
public class Test {


    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView test(HttpServletRequest req, ModelMap map){


        UserService userSer = UserServiceFactory.getUserService();
        User currentUser = userSer.getCurrentUser();

       // if (currentUser != null) {
         //   String userName = currentUser.getEmail();
            Result<user> user = ofy().load().key(Key.create(user.class, "hello")); //query to make check if the user is in the db or not

            if (user.now() == null) {// if not in the db then create it
                user newUser = new user();//create a new user to with its email and score set to 0 to be save to db later
                newUser.setEmail("hello");
                newUser.setScore(0);
                ofy().save().entity(newUser).now();//save the new user in db
            }

            //increase the user's score by one
            Result<user> player = ofy().load().key(Key.create(user.class, "hello"));
            user userInDB = player.now();
            userInDB.setScore(userInDB.getScore() + 1);
            ofy().save().entity(userInDB).now();

            System.out.println(userInDB.getEmail() + " score: " + userInDB.getScore());
        //}

        return new ModelAndView("test");

    }

}
