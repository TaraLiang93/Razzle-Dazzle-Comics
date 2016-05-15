package com.rdc;

import com.data.Globals;
import com.data.UserData;
import com.data.api.createables.GenreCreater;
import com.data.api.createables.UserDataCreater;
import com.data.api.createables.fillCommands.GenreFillCommand;
import com.data.api.createables.fillCommands.UserDataFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.external.GetUserDataByIDCommand;
import com.data.api.updatables.UserDataUpdater;
import com.data.structure.Genre;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class LoginController {


    public static final String LOGIN = "/login";
    public static final String HOME = "/";
    public static final String LOGOUT = "/logout";
    public static final int GENRE_SIZE = 16;

    @RequestMapping(value=HOME, method= RequestMethod.GET)
    public ModelAndView homepage(HttpSession session, ModelMap map){

        if(session.getAttribute("userData") == null && session.getAttribute("loginURL") == null)
            session.setAttribute("loginURL",LOGIN);

        Globals globals = (Globals) session.getAttribute("globals");
        if(globals == null)
        {
            globals = new Globals();
            session.setAttribute("globals",globals);
        }
        globals.setStatus("home");

        List<Genre> globalsGenres  = ofy().load().type(Genre.class).list();

        if(ofy().load().type(Genre.class).list().size() != GENRE_SIZE) {
            try {

                if(ofy().load().type(Genre.class).keys().list().size() != 0) {// deletes all previous entries
                    List<Key<Genre>> keys = ofy().load().type(Genre.class).keys().list();
                    ofy().delete().keys(keys).now();
                }
                ArrayList<String> genres = new ArrayList<>();
                genres.add("Action");
                genres.add("Crime");
                genres.add("Political");
                genres.add("Drama");
                genres.add("Romance");
                genres.add("SuperHero");
                genres.add("Fantasy");
                genres.add("War");
                genres.add("Western");
                genres.add("Science Fiction");
                genres.add("Alternative");
                genres.add("Comedy");
                genres.add("Tradegy");
                genres.add("Slice of life");
                genres.add("Horror");
                genres.add("Documentary");



                for (int i = 0; i < genres.size(); i++) {
                    Createable<Genre> genreCreateable = new GenreCreater(genres.get(i));
                    genreCreateable.createEntity(new GenreFillCommand());
                }
                globalsGenres  = ofy().load().type(Genre.class).list();
//            for( Genre genre : ofy().load().type(Genre.class).list()){
//                System.out.println( genre.getName() );
//            }
            } catch (CreateException | FetchException e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("homepage");
    }


    @RequestMapping(value =LOGIN, method= RequestMethod.GET)
    public String login(@RequestHeader("Referer") String ref, HttpSession session, ModelMap map){

        Globals globals = (Globals) session.getAttribute("globals");
        if(globals == null)
        {
            globals = new Globals();
            session.setAttribute("globals",globals);
        }
        globals.setAuth(true);

        UserService userService = UserServiceFactory.getUserService();

            session.setAttribute("loginURL",null);

        session.setAttribute("logoutURL", LOGOUT);

        return "redirect:" + userService.createLoginURL("/setUser"+"?ref=" + ref);

    }

    @RequestMapping(value ="/setUser", method= RequestMethod.GET)
    public String setUserName(@RequestParam String ref,HttpSession session)
    {
        UserService userService = UserServiceFactory.getUserService();
        UserData userData = null;
        try {

            Readable<UserData> getUserDataReadable = new GetUserDataByIDCommand(userService.getCurrentUser().getUserId());


            if (( userData=getUserDataReadable.fetch().getResult()) == null ) {// if the user doesn't exist
                Createable<UserData> userDataCreateable = new UserDataCreater(userService.getCurrentUser());

                userData = userDataCreateable.createEntity(new UserDataFillCommand());
                System.out.println("It created a new User");
            }
        } catch (FetchException | CreateException ex)
        {
            ref = HOME; //Redirect back to login page if failed to create User Data
            ex.printStackTrace();
        }

        session.setAttribute("userData", userData);


        return "redirect:"+ ref;
    }

    @RequestMapping(value =LOGOUT, method= RequestMethod.GET)
    public String logout(HttpSession session, ModelMap map){

        Globals globals = (Globals) session.getAttribute("globals");
        if(globals == null)
        {
            globals = new Globals();
            session.setAttribute("globals",globals);
        }
        globals.setAuth(false);

        UserService userService = UserServiceFactory.getUserService();

        if(session.getAttribute("logoutURL") != null)
            session.removeAttribute("logoutURL");

        session.removeAttribute("userData");


        return "redirect:" + userService.createLogoutURL(HOME);

    }




}
