package com.rdc;

import com.data.UserData;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.cmd.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

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
        UserData data = new UserData();
        data.setNickName("James");
        ofy().save().entity(data).now();

        UserData user = ofy().load().type(UserData.class).filter("nickName", "James").first().now();
        System.out.println("Found User : " + user);

//        Query<UserData> all = ofy().load().type(UserData.class);


//        Query<UserData> q = ofy().load().type(UserData.class).filter("nickName >", "123456789");
       // if (currentUser != null) {
         //   String userName = currentUser.getEmail();
      //      Result<UserData> user = ofy().load().key(Key.create(UserData.class, "hello")); //query to make check if the User is in the db or not
/*
            if (user.now() == null) {// if not in the db then create it
                UserData newUserData = new UserData();//create a new User to with its email and score set to 0 to be save to db later
                newUserData.setEmail("hello");
                newUserData.setScore(0);
                ofy().save().entity(newUserData).now();//save the new User in db
            }

            //increase the User's score by one
            Result<UserData> player = ofy().load().key(Key.create(UserData.class, "hello"));
            UserData userDataInDB = player.now();
            userDataInDB.setScore(userDataInDB.getScore() + 1);
            ofy().save().entity(userDataInDB).now();

            System.out.println(userDataInDB.getEmail() + " score: " + userDataInDB.getScore());
*/        //}

        return new ModelAndView("test");

    }

}
