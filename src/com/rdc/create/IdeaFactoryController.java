package com.rdc.create;

import com.data.creation.Doodle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by drodrigues on 3/29/16.
 */
@Controller
public class IdeaFactoryController {

    public static final String IDEA_HOME = "/create/ideas";
    public static final String LOAD_SCRIBBLE = "/create/scribble/load/{id}";
    public static final String LOAD_DOODLE = "/create/doodle/load/{id}";

    @RequestMapping(value=IDEA_HOME, method= RequestMethod.GET)
    public ModelAndView loadIdeaFactory(HttpSession session, ModelMap map){

        Doodle doodle1 = new Doodle();
        doodle1.setTitle("Ayyyy");
        doodle1.setDescription("Awww Yeahhh");

        Doodle doodle2 = new Doodle();
        doodle2.setTitle("Beeeee");
        doodle2.setDescription("Be Bop Boop");

        Doodle doodle3 = new Doodle();
        doodle3.setTitle("Seeee");
        doodle3.setDescription("See ya next tuesday");

        List<Doodle> doodles = new LinkedList<>();

        doodles.add(doodle1);
        doodles.add(doodle2);
        doodles.add(doodle3);

        map.put("doodles", doodles);
        map.put("scribbles", doodles);

        return new ModelAndView("ideaFactory");
    }


    @RequestMapping(value=LOAD_SCRIBBLE, method= RequestMethod.GET)
    public ModelAndView loadScribble(@PathVariable String id, HttpSession session, ModelMap map){

        System.out.println("ID : " + id);
        if(id != null && id.equals("new")){
            System.out.println("New!");
        }
        else{
            System.out.println("Old!");
        }

        return new ModelAndView("homepage");
    }

    @RequestMapping(value=LOAD_DOODLE, method= RequestMethod.GET)
    public ModelAndView loadDoodle(@PathVariable String id, HttpSession session, ModelMap map){

        System.out.println("ID : " + id);
        if(id != null && id.equals("new")){
            System.out.println("New!");
        }
        else{
            System.out.println("Old!");
        }

        return new ModelAndView("homepage");
    }

    @RequestMapping(value="/create/dooddle/new", method= RequestMethod.GET)
    public ModelAndView newDoodle(HttpSession session,ModelMap map){

        return new ModelAndView("doodles");
    }

    @RequestMapping(value="/create/dooddle/save", method= RequestMethod.POST)
    public String saveDoodle(HttpServletRequest req, HttpSession session, ModelMap map){

        if(req != null)
        {
            System.out.println("Data came here");
        }

        return "OK";
    }

}
